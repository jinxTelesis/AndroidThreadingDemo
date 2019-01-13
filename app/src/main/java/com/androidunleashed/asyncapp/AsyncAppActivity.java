package com.androidunleashed.asyncapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.AsyncTask;

public class AsyncAppActivity extends Activity {
    TextView seqView;
    ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_app);
        seqView=(TextView) findViewById(R.id.sequenceview);
        //pb=(ProgressBar)findViewById(R.id.progress);
        new PrintSequenceTask().execute(1);
    }

    private class PrintSequenceTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            seqView.setText("Sequence numbers begins");
        }

        @Override
        protected Void doInBackground(Integer... args) {
            for (int i = args[0]; i <= 100; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... args) {
            seqView.setText(args[0].toString());
            //pb.setProgress(args[0]);
        } 

        @Override
        protected void onPostExecute(Void result) {
            seqView.setText("Sequence numbers over");
        }
    }
}
