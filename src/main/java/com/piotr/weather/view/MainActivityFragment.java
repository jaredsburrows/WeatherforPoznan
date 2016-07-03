package com.piotr.weather.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.piotr.weather.R;
import com.piotr.weather.WeatherApplication;
import com.piotr.weather.adapter.ForecastAdapter;
import com.piotr.weather.model.Forecast;
import com.piotr.weather.receiver.NotificationEventReceiver;
import com.piotr.weather.repositories.WeatherDatabaseRepository;
import com.piotr.weather.service.WeatherService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.activeandroid.Cache.getContext;
import static com.piotr.weather.WeatherApplication.weatherAPI;

@EFragment(R.layout.fragment_main)
public class MainActivityFragment extends Fragment {

    private final WeatherDatabaseRepository mDatabaseRepository = new WeatherDatabaseRepository();

    @StringRes
    String API_ID;

    @ViewById
    ListView mListView;

    @ViewById
    SwipeRefreshLayout swipeRefresh;

    public ForecastAdapter getForecastAdapter() {
        return mForecastAdapter;
    }

    public void setForecastAdapter(ForecastAdapter forecastAdapter) {
        mForecastAdapter = forecastAdapter;
    }

    private ForecastAdapter mForecastAdapter;

    @AfterViews
    public void onCreateMainActivityFragment() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openForecastDetailsActivity(position);
            }
        });
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        downloadForecastData(weatherAPI);
                    }
                }
        );
        downloadForecastData(weatherAPI);
        //FixMe: If offline, download data from database
        downloadCityData(weatherAPI);
    }

    public void openForecastDetailsActivity(int position) {
        long forecastItemId = mForecastAdapter.getItem(position).getId();
        Intent intent = new Intent(getContext(), DetailsActivity_.class);
        intent.putExtra("id", forecastItemId);
        startActivity(intent);
    }

    @UiThread
    protected void downloadForecastData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(5400075, "json", "imperial", "hour", "en", API_ID, new
                Callback<Forecast>() {
                    @Override
                    public void success(Forecast forecast, Response response) {
                        if (getActivity() != null) {
                            mForecastAdapter = new ForecastAdapter(getActivity(),
                                    forecast.getForecastList());
                            mListView.setAdapter(mForecastAdapter);
                            swipeRefresh.setRefreshing(false);

                            //ActiveAndroid implementation
                            mDatabaseRepository.saveForecastItemToDatabase(forecast);

                            Log.d("WeatherApplication", "Forecast: " + forecast.getForecastList());
                            Log.d("DATABASE", "WeatherApplication: " + WeatherApplication.getObjectsList());

                            NotificationEventReceiver.setupAlarm(getContext());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Failure", "error: " + error);
                        swipeRefresh.setRefreshing(false);
                        showErrorSnackbar(weatherAPI);
                    }
                }

        );
    }

    @UiThread
    protected void downloadCityData(final WeatherService weatherAPI) {
        weatherAPI.getForecast(5400075, "json", "imperial", "hour", "en", API_ID, new Callback<Forecast>() {
            @Override
            public void success(Forecast forecast, Response response) {
                mDatabaseRepository.saveCityDataToDatabase(forecast);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", "error: " + error);
                swipeRefresh.setRefreshing(false);
                showErrorSnackbar(weatherAPI);
            }
        });
    }

    private void showErrorSnackbar(final WeatherService weatherAPI) {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_download_data, Snackbar.LENGTH_LONG)
                    .setAction(R.string.error_again_message, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            downloadForecastData(weatherAPI);
                        }
                    }).show();
        }
    }
}
