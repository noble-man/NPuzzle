package n.puzzle.oathisus;

import java.util.Random;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.SystemClock;
import android.util.Log;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import static n.puzzle.oathisus.R.id.info;


/*
*
* Story Idea : A detective is carrying out an investigation. on a murder scene.. where an explosion took place
 * 				He needs to put the torn pieces of the evidence back together. Drawings (husband draws wife), Archeological pieces...
* 				The goal is to find the murderer.
*
		* 		There's a story...
*
*
* */


//The games story might be a version of Romeo and Juliet in Wakanda. Wakandan Romeo and Juliet.


//CHIFFRES ET IMAGES.
//REMET JUSTE LE BEST DU GARS... (PAS DE DB POUR TOUS LES JOUEURS)
//no more than 31 single-tile moves
public class EasyActivity extends AppCompatActivity {

	private ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b0;

	private TextView tv_moves, tv_timerValue;

	private int tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile0;

	private int im1, im2, im3, im4, im5, im6, im7, im8, im0;

	private Random r;

	private int temp;

	private Typeface tf;

	private int moves = 0;

	private long startTime = 0L; //Code na ngai
	private Handler customHandler = new Handler();


	private long timeInMilliseconds = 0L;//Code na ngai
	private long timeSwapBuff = 0L;//Code na ngai
	private long updatedTime = 0L;//Code na ngai


	private int best = 0;
	//long best_time ;
	private int best_time;

	private boolean changed = false; // if the tile is moved

	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Log.v("HiPOBA", "la connexion était successful: ");

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy);

		// Create the interstitial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-7124948529897131/7484017687");

		// Create ad request.
		AdRequest adRequest = new AdRequest.Builder().build();

		// Begin loading your interstitial.
		interstitial.loadAd(adRequest);

		SharedPreferences settings = getSharedPreferences("PREFS", 0);
		best = settings.getInt("best_easy", 0);
		best_time = settings.getInt("best_time_easy",0);
		//best_time = settings.getLong("best_time_easy",0L);

		tf = Typeface.createFromAsset(getAssets(), "font.ttf");

		r = new Random();

		tv_moves = (TextView) findViewById(R.id.tv_moves);
		tv_moves.setTypeface(tf);

		im1 = R.drawable.tile_1;
		im2 = R.drawable.tile_2;
		im3 = R.drawable.tile_3;
		im4 = R.drawable.tile_4;
		im5 = R.drawable.tile_5;
		im6 = R.drawable.tile_6;
		im7 = R.drawable.tile_7;
		im8 = R.drawable.tile_8;
		im0 = R.drawable.tile_0;

		b1 = (ImageButton) findViewById(R.id.imageButton1);
		b2 = (ImageButton) findViewById(R.id.imageButton2);
		b3 = (ImageButton) findViewById(R.id.imageButton3);
		b4 = (ImageButton) findViewById(R.id.imageButton4);
		b5 = (ImageButton) findViewById(R.id.imageButton5);
		b6 = (ImageButton) findViewById(R.id.imageButton6);
		b7 = (ImageButton) findViewById(R.id.imageButton7);
		b8 = (ImageButton) findViewById(R.id.imageButton8);
		b0 = (ImageButton) findViewById(R.id.imageButton0);

		tile1 = 1;
		tile2 = 2;
		tile3 = 3;
		tile4 = 4;
		tile5 = 5;
		tile6 = 6;
		tile7 = 7;
		tile8 = 8;
		tile0 = 0;

		scramble();

		setImages();

		moves = 0;
		startTime = SystemClock.uptimeMillis(); //Code na ngai
		//Log.d("ERR NINI","il est : "+startTime);//Code na ngai
		customHandler.postDelayed(updateTimerThread, 0);


		//customHandler.postDelayed(updateTimerThread, 0);//Code na ngai

		tv_moves.setText("Best: " + best + "    Moves: " + moves);//Code ya kala
		//tv_moves.setText("Timer: " + time + "    Moves: " + moves);//Code ya kala

		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b1action();
				setImages();
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b2action();
				setImages();
			}
		});
		b3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b3action();
				setImages();
			}
		});
		b4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b4action();
				setImages();
			}
		});
		b5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b5action();
				setImages();
			}
		});
		b6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b6action();
				setImages();
			}
		});
		b7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b7action();
				setImages();
			}
		});
		b8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b8action();
				setImages();
			}
		});
		b0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b0action();
				setImages();
			}
		});
	}

	public void b1action() {
		if (tile2 == 0) {
			tile2 = tile1;
			tile1 = 0;
			changed = true;
		}
		if (tile4 == 0) {
			tile4 = tile1;
			tile1 = 0;
			changed = true;
		}
	}

	public void b2action() {
		if (tile1 == 0) {
			tile1 = tile2;
			tile2 = 0;
			changed = true;
		}
		if (tile3 == 0) {
			tile3 = tile2;
			tile2 = 0;
			changed = true;
		}
		if (tile5 == 0) {
			tile5 = tile2;
			tile2 = 0;
			changed = true;
		}
	}

	public void b3action() {
		if (tile2 == 0) {
			tile2 = tile3;
			tile3 = 0;
			changed = true;
		}
		if (tile6 == 0) {
			tile6 = tile3;
			tile3 = 0;
			changed = true;
		}
	}

	public void b4action() {
		if (tile1 == 0) {
			tile1 = tile4;
			tile4 = 0;
			changed = true;
		}
		if (tile5 == 0) {
			tile5 = tile4;
			tile4 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile4;
			tile4 = 0;
			changed = true;
		}
	}

	public void b5action() {
		if (tile2 == 0) {
			tile2 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile4 == 0) {
			tile4 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile6 == 0) {
			tile6 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile5;
			tile5 = 0;
			changed = true;
		}
	}

	public void b6action() {
		if (tile3 == 0) {
			tile3 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile5 == 0) {
			tile5 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile6;
			tile6 = 0;
			changed = true;
		}
	}

	public void b7action() {
		if (tile4 == 0) {
			tile4 = tile7;
			tile7 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile7;
			tile7 = 0;
			changed = true;
		}
	}

	public void b8action() {
		if (tile5 == 0) {
			tile5 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile8;
			tile8 = 0;
			changed = true;
		}
	}

	public void b0action() {
		if (tile6 == 0) {
			tile6 = tile0;
			tile0 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile0;
			tile0 = 0;
			changed = true;
		}
	}

	public void setImages() {
		if (tile1 == 1) {
			b1.setImageResource(im1);
		} else if (tile1 == 2) {
			b1.setImageResource(im2);
		} else if (tile1 == 3) {
			b1.setImageResource(im3);
		} else if (tile1 == 4) {
			b1.setImageResource(im4);
		} else if (tile1 == 5) {
			b1.setImageResource(im5);
		} else if (tile1 == 6) {
			b1.setImageResource(im6);
		} else if (tile1 == 7) {
			b1.setImageResource(im7);
		} else if (tile1 == 8) {
			b1.setImageResource(im8);
		} else if (tile1 == 0) {
			b1.setImageResource(im0);
		}

		if (tile2 == 1) {
			b2.setImageResource(im1);
		} else if (tile2 == 2) {
			b2.setImageResource(im2);
		} else if (tile2 == 3) {
			b2.setImageResource(im3);
		} else if (tile2 == 4) {
			b2.setImageResource(im4);
		} else if (tile2 == 5) {
			b2.setImageResource(im5);
		} else if (tile2 == 6) {
			b2.setImageResource(im6);
		} else if (tile2 == 7) {
			b2.setImageResource(im7);
		} else if (tile2 == 8) {
			b2.setImageResource(im8);
		} else if (tile2 == 0) {
			b2.setImageResource(im0);
		}

		if (tile3 == 1) {
			b3.setImageResource(im1);
		} else if (tile3 == 2) {
			b3.setImageResource(im2);
		} else if (tile3 == 3) {
			b3.setImageResource(im3);
		} else if (tile3 == 4) {
			b3.setImageResource(im4);
		} else if (tile3 == 5) {
			b3.setImageResource(im5);
		} else if (tile3 == 6) {
			b3.setImageResource(im6);
		} else if (tile3 == 7) {
			b3.setImageResource(im7);
		} else if (tile3 == 8) {
			b3.setImageResource(im8);
		} else if (tile3 == 0) {
			b3.setImageResource(im0);
		}

		if (tile4 == 1) {
			b4.setImageResource(im1);
		} else if (tile4 == 2) {
			b4.setImageResource(im2);
		} else if (tile4 == 3) {
			b4.setImageResource(im3);
		} else if (tile4 == 4) {
			b4.setImageResource(im4);
		} else if (tile4 == 5) {
			b4.setImageResource(im5);
		} else if (tile4 == 6) {
			b4.setImageResource(im6);
		} else if (tile4 == 7) {
			b4.setImageResource(im7);
		} else if (tile4 == 8) {
			b4.setImageResource(im8);
		} else if (tile4 == 0) {
			b4.setImageResource(im0);
		}

		if (tile5 == 1) {
			b5.setImageResource(im1);
		} else if (tile5 == 2) {
			b5.setImageResource(im2);
		} else if (tile5 == 3) {
			b5.setImageResource(im3);
		} else if (tile5 == 4) {
			b5.setImageResource(im4);
		} else if (tile5 == 5) {
			b5.setImageResource(im5);
		} else if (tile5 == 6) {
			b5.setImageResource(im6);
		} else if (tile5 == 7) {
			b5.setImageResource(im7);
		} else if (tile5 == 8) {
			b5.setImageResource(im8);
		} else if (tile5 == 0) {
			b5.setImageResource(im0);
		}

		if (tile6 == 1) {
			b6.setImageResource(im1);
		} else if (tile6 == 2) {
			b6.setImageResource(im2);
		} else if (tile6 == 3) {
			b6.setImageResource(im3);
		} else if (tile6 == 4) {
			b6.setImageResource(im4);
		} else if (tile6 == 5) {
			b6.setImageResource(im5);
		} else if (tile6 == 6) {
			b6.setImageResource(im6);
		} else if (tile6 == 7) {
			b6.setImageResource(im7);
		} else if (tile6 == 8) {
			b6.setImageResource(im8);
		} else if (tile6 == 0) {
			b6.setImageResource(im0);
		}

		if (tile7 == 1) {
			b7.setImageResource(im1);
		} else if (tile7 == 2) {
			b7.setImageResource(im2);
		} else if (tile7 == 3) {
			b7.setImageResource(im3);
		} else if (tile7 == 4) {
			b7.setImageResource(im4);
		} else if (tile7 == 5) {
			b7.setImageResource(im5);
		} else if (tile7 == 6) {
			b7.setImageResource(im6);
		} else if (tile7 == 7) {
			b7.setImageResource(im7);
		} else if (tile7 == 8) {
			b7.setImageResource(im8);
		} else if (tile7 == 0) {
			b7.setImageResource(im0);
		}

		if (tile8 == 1) {
			b8.setImageResource(im1);
		} else if (tile8 == 2) {
			b8.setImageResource(im2);
		} else if (tile8 == 3) {
			b8.setImageResource(im3);
		} else if (tile8 == 4) {
			b8.setImageResource(im4);
		} else if (tile8 == 5) {
			b8.setImageResource(im5);
		} else if (tile8 == 6) {
			b8.setImageResource(im6);
		} else if (tile8 == 7) {
			b8.setImageResource(im7);
		} else if (tile8 == 8) {
			b8.setImageResource(im8);
		} else if (tile8 == 0) {
			b8.setImageResource(im0);
		}

		if (tile0 == 1) {
			b0.setImageResource(im1);
		} else if (tile0 == 2) {
			b0.setImageResource(im2);
		} else if (tile0 == 3) {
			b0.setImageResource(im3);
		} else if (tile0 == 4) {
			b0.setImageResource(im4);
		} else if (tile0 == 5) {
			b0.setImageResource(im5);
		} else if (tile0 == 6) {
			b0.setImageResource(im6);
		} else if (tile0 == 7) {
			b0.setImageResource(im7);
		} else if (tile0 == 8) {
			b0.setImageResource(im8);
		} else if (tile0 == 0) {
			b0.setImageResource(im0);
		}

		if (changed == true) {
			moves = moves + 1;
			changed = false;
		}
		tv_moves.setText("Best: " + best + "    Moves: " + moves);

		if (tile1 == 1 && tile2 == 2 && tile3 == 3 && tile4 == 4 && tile5 == 5
				&& tile6 == 6 && tile7 == 7 && tile8 == 8) {


			try {

				SharedPreferences settings = getSharedPreferences("PREFS", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putBoolean("completed_easy", true);

				if (moves < best || best == 0) {
					//SharedPreferences settings = getSharedPreferences("PREFS", 0);
					//SharedPreferences.Editor editor = settings.edit();
					editor.putInt("best_easy", moves);
					editor.commit();
				}
				//	if (updatedTime < best_time || best_time == 0L) {
				//SharedPreferences settings = getSharedPreferences("PREFS", 0);
				//SharedPreferences.Editor editor = settings.edit();
				editor.putInt("best_time_easy", moves);
				//editor.putLong("best_time_easy", moves);
				editor.commit();
				//	}


				int secs = (int) (updatedTime / 1000);

				int mins = secs / 60;

				secs = secs % 60;

				int milliseconds = (int) (updatedTime % 1000);
				String clock = String.format("%02d", mins) + ":" + String.format("%02d", secs);




			//Class.forName("com.mysql.jdbc.Driver");
		/*	Connection conn = null;
			try {
				//jdbc:postgresql://host:port/database
				//conn = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname", "username", "password");
				//conn = DriverManager.getConnection("jdbc:postgres://vkmzyqeduhyyqc:tnk21k9m5Q6v38W4BWlBxkhSj_@ec2-107-22-184-127.compute-1.amazonaws.com:5432/d8bo5ojv49sppc", "vkmzyqeduhyyqc", "tnk21k9m5Q6v38W4BWlBxkhSj_");


				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myNPuzzle", "root","");


				Log.v("CONNOK", "la connexion était successful: ");
				conn.close();
			}
			catch (Exception e){

				Log.v("ERRCONN", "erreur connection DB : " + e);

			}*/



			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					EasyActivity.this);
			alertDialogBuilder
					.setMessage(
							"You successfully completed the puzzle in "+ clock +" and " + moves
									+ " moves!")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									displayInterstitial();
									finish();
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();


			}
			catch (Exception e){
				Log.v("marois","jdfuraùarera" +e);
			}
		}
	}

	public void scramble() {
		for (int i = 0; i < 100000; i++) {
			temp = r.nextInt(24);

			if (temp == 0) {
				if (tile2 == 0) {
					tile2 = tile1;
					tile1 = 0;
				}
			}
			if (temp == 1) {
				if (tile4 == 0) {
					tile4 = tile1;
					tile1 = 0;
				}
			}
			if (temp == 2) {
				if (tile1 == 0) {
					tile1 = tile2;
					tile2 = 0;
				}
			}
			if (temp == 3) {
				if (tile3 == 0) {
					tile3 = tile2;
					tile2 = 0;
				}
			}
			if (temp == 4) {
				if (tile5 == 0) {
					tile5 = tile2;
					tile2 = 0;
				}
			}
			if (temp == 5) {
				if (tile2 == 0) {
					tile2 = tile3;
					tile3 = 0;
				}
			}
			if (temp == 6) {
				if (tile6 == 0) {
					tile6 = tile3;
					tile3 = 0;
				}
			}
			if (temp == 7) {
				if (tile1 == 0) {
					tile1 = tile4;
					tile4 = 0;
				}
			}
			if (temp == 8) {
				if (tile5 == 0) {
					tile5 = tile4;
					tile4 = 0;
				}
			}
			if (temp == 9) {
				if (tile7 == 0) {
					tile7 = tile4;
					tile4 = 0;
				}
			}
			if (temp == 10) {
				if (tile2 == 0) {
					tile2 = tile5;
					tile5 = 0;
				}
			}
			if (temp == 11) {
				if (tile4 == 0) {
					tile4 = tile5;
					tile5 = 0;
				}
			}
			if (temp == 12) {
				if (tile6 == 0) {
					tile6 = tile5;
					tile5 = 0;
				}
			}
			if (temp == 13) {
				if (tile8 == 0) {
					tile8 = tile5;
					tile5 = 0;
				}
			}
			if (temp == 14) {
				if (tile3 == 0) {
					tile3 = tile6;
					tile6 = 0;
				}
			}
			if (temp == 15) {
				if (tile5 == 0) {
					tile5 = tile6;
					tile6 = 0;
				}
			}
			if (temp == 16) {
				if (tile0 == 0) {
					tile0 = tile6;
					tile6 = 0;
				}
			}
			if (temp == 17) {
				if (tile4 == 0) {
					tile4 = tile7;
					tile7 = 0;
				}
			}
			if (temp == 18) {
				if (tile8 == 0) {
					tile8 = tile7;
					tile7 = 0;
				}
			}
			if (temp == 19) {
				if (tile5 == 0) {
					tile5 = tile8;
					tile8 = 0;
				}
			}
			if (temp == 20) {
				if (tile7 == 0) {
					tile7 = tile8;
					tile8 = 0;
				}
			}
			if (temp == 21) {
				if (tile0 == 0) {
					tile0 = tile8;
					tile8 = 0;
				}
			}
			if (temp == 22) {
				if (tile6 == 0) {
					tile6 = tile0;
					tile0 = 0;
				}
			}
			if (temp == 23) {
				if (tile8 == 0) {
					tile8 = tile0;
					tile0 = 0;
				}
			}

		}
	}

	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		displayInterstitial();
		finish();
	}

	private Runnable updateTimerThread = new Runnable() {//code Na ngai

		public void run() {

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime = timeSwapBuff + timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);

			int mins = secs / 60;

			secs = secs % 60;

			int milliseconds = (int) (updatedTime % 1000);

			String clock = String.format("%02d",mins) +":"+ String.format("%02d", secs) ;//+ "." + String.format("%03d", milliseconds) ;

			tv_moves.setText(/*"Time: " +*/clock+ "    Moves: " + moves);
			/*timerValue.setText("" + mins + ":"

							+ String.format("%02d", secs) + ":"

							+ String.format("%03d", milliseconds));*/

			customHandler.postDelayed(this, 0);

		}

	};

}
