package com.yasin.restapicall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yasin.restapicall.entity.Greeting;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreteView(LayoutInflater inflater, ViewGroup container, Bundle saveInstancesState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }
}

public class HttpRequestTaxk extends AsyncTask<Void ,Void, Greeting>{

    @Override
    protected Greeting doInBackground(Void... params) {
       try {
           final String url = "http://rest-service.guides.spring.io/greeting";
           RestTemplate restTemplate = new RestTemplate();
           restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
           Greeting greeting = restTemplate.getForObject(url,Greeting.class);
           return greeting;

       }catch (Exception e){
           Long.e("MainActivity",e.getMessage());

       }
       return null;
    }

    @Override
    protected void onPostExecute(Greeting greeting) {
        TextView greetingIdText = (TextView) findViewById(R.id.id_value);
        TextView greetingContentText = (TextView) findViewById(R.id.id_value);
        greetingIdText.setText(greeting.getId());
        greetingContentText.setText(greeting.getContent());
    }
}