package org.schabi.newpipe;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomDownloaderActivity extends AppCompatActivity {
    private EditText linkInput;
    private Button downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_downloader);

        linkInput = findViewById(R.id.link_input);
        downloadBtn = findViewById(R.id.download_btn);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = linkInput.getText().toString().trim();
                if (url.isEmpty()) {
                    Toast.makeText(CustomDownloaderActivity.this, "لینک را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setTitle("دانلود فایل");
                    request.setDescription(url);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloaded_file");

                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);

                    Toast.makeText(CustomDownloaderActivity.this, "دانلود شروع شد", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(CustomDownloaderActivity.this, "خطا در شروع دانلود", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
