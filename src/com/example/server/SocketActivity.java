package com.example.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketActivity extends Activity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socket);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new ServerThread().start();
			}
		});
	}

	class ServerThread extends Thread {
		@Override
		public void run() {
			ServerSocket serverSocket = null;
			System.out.println("run()");
			try {
				// create a ServerSocket object,and listen at port 4567
				serverSocket = new ServerSocket(4567);
				System.out.println("aaaaaa");
				// ServerSocket waits for client connect
				Socket socket = serverSocket.accept();

				// get inputstream from socket
				InputStream inputStream = socket.getInputStream();
				byte buffer[] = new byte[1024 * 4];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					String result = new String(buffer, 0, len);
					System.out.println("result------>" + result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (serverSocket != null) {
						serverSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
