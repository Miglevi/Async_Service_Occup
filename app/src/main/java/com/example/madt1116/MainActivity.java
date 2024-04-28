package com.example.madt1116;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.madt1116.DataManager;
import com.example.madt1116.DataLoader;
import com.example.madt1116.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;
    private Switch swUseAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lvItems = findViewById(R.id.lv_items);
        this.tvStatus = findViewById(R.id.tvContent);
        this.swUseAsyncTask = findViewById(R.id.swich_async);
        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);
    }
    public void onBtnDownloadClick(View view) {
        this.tvStatus.setText(R.string.loading_data);
        if(this.swUseAsyncTask.isChecked()){
            getDataByAsyncTask();
            Toast.makeText(this, R.string.msg_using_async_task, Toast.LENGTH_LONG).show();
        }
        else{
            getDataByThread();
            Toast.makeText(this, R.string.msg_using_thread, Toast.LENGTH_LONG).show();
        }
    }
    public void getDataByAsyncTask(){
        new DataLoader() {
            @Override
            public void onPostExecute(String result) {
                tvStatus.setText(getString(R.string.data_loaded) + result);
            }
        }.execute(Constants.Service_occup);
    }
    public void getDataByThread() {
        this.tvStatus.setText(R.string.loading_data);
        Runnable getDataAndDisplayRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    final String result = DataManager.getValuesFromApi(Constants.Service_occup);
                    Runnable updateUIRunnable = new Runnable() {
                        @Override
                        public void run() {
                            tvStatus.setText(getString(R.string.data_loaded) + result);
                        }
                    };
                    runOnUiThread(updateUIRunnable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(getDataAndDisplayRunnable);
        thread.start();
    }
}