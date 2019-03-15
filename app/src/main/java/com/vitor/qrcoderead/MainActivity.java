package com.vitor.qrcoderead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView conteudoQRCode;
    private Button btn_readQrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conteudoQRCode = (TextView) findViewById(R.id.qr_conteudo);
        btn_readQrCode = (Button) findViewById(R.id.btn_qrCode);

        btn_readQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanQr = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(scanQr);
            }
        });
    }
}
