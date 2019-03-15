package com.vitor.qrcoderead;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    private RelativeLayout rl;
    private Button btn_back_QRCode;
    private Runnable runnable;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        rl = (RelativeLayout) findViewById(R.id.relative_scan_take_single);
        mScannerView = new ZBarScannerView(this);
        rl.addView(mScannerView);

        btn_back_QRCode = (Button) findViewById(R.id.btn_back_scanQr);
        btn_back_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(1);
        timeOutInQRScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void timeOutInQRScan(){
        runnable = new Runnable() {
            @Override
            public void run() {
                onBackPressed();
                Toast.makeText(ScanActivity.this,"Erro Leitura QRCODE", Toast.LENGTH_SHORT).show();
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable,30000);
    }

    @Override
    public void handleResult(Result result) {

      final String conteudo = result.getContents();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.conteudoQRCode.setText(conteudo);
            }
        });

        onBackPressed();
    }
}
