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

//LIMITER PLUS SEVEREMENT LE NOMBRE DE MOVES : OK
//LIMITER LE TEMPS : OK
//208 single-tile moves
//Images avec illusion d'optique
public class HardActivity extends AppCompatActivity {

	ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14,
			b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b0;

	TextView tv_moves;

	int tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
			tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18,
			tile19, tile20, tile21, tile22, tile23, tile24, tile0;

	int im1, im2, im3, im4, im5, im6, im7, im8, im9, im10, im11, im12, im13,
			im14, im15, im16, im17, im18, im19, im20, im21, im22, im23, im24,
			im0;

	Random r;

	int temp;
	final static int maxNumberOfMoves = 208;
	final static int maxTimeInMillis = 156*1000;// 0.75sec par move

	Typeface tf;

	int moves = 0;

	long startTime = 0L; //Code na ngai
	private Handler customHandler = new Handler();


	long timeInMilliseconds = 0L;//Code na ngai
	long timeSwapBuff = 0L;//Code na ngai
	long updatedTime = 0L;//Code na ngai


	int best = 0;
	//long best_time ;
	int best_time;

	boolean changed = false; // if the tile is moved

	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hard);

		// Create the interstitial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-7124948529897131/7484017687");

		// Create ad request.
		AdRequest adRequest = new AdRequest.Builder().build();

		// Begin loading your interstitial.
		interstitial.loadAd(adRequest);

		SharedPreferences settings = getSharedPreferences("PREFS", 0);
		best = settings.getInt("best_hard", 0);
		best_time = settings.getInt("best_time_hard",0);
		//best_time = settings.getLong("best_time_hard",0L);

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
		im9 = R.drawable.tile_9;
		im10 = R.drawable.tile_10;
		im11 = R.drawable.tile_11;
		im12 = R.drawable.tile_12;
		im13 = R.drawable.tile_13;
		im14 = R.drawable.tile_14;
		im15 = R.drawable.tile_15;
		im16 = R.drawable.tile_16;
		im17 = R.drawable.tile_17;
		im18 = R.drawable.tile_18;
		im19 = R.drawable.tile_19;
		im20 = R.drawable.tile_20;
		im21 = R.drawable.tile_21;
		im22 = R.drawable.tile_22;
		im23 = R.drawable.tile_23;
		im24 = R.drawable.tile_24;
		im0 = R.drawable.tile_0;

		b1 = (ImageButton) findViewById(R.id.imageButton1);
		b2 = (ImageButton) findViewById(R.id.imageButton2);
		b3 = (ImageButton) findViewById(R.id.imageButton3);
		b4 = (ImageButton) findViewById(R.id.imageButton4);
		b5 = (ImageButton) findViewById(R.id.imageButton5);
		b6 = (ImageButton) findViewById(R.id.imageButton6);
		b7 = (ImageButton) findViewById(R.id.imageButton7);
		b8 = (ImageButton) findViewById(R.id.imageButton8);
		b9 = (ImageButton) findViewById(R.id.imageButton9);
		b10 = (ImageButton) findViewById(R.id.imageButton10);
		b11 = (ImageButton) findViewById(R.id.imageButton11);
		b12 = (ImageButton) findViewById(R.id.imageButton12);
		b13 = (ImageButton) findViewById(R.id.imageButton13);
		b14 = (ImageButton) findViewById(R.id.imageButton14);
		b15 = (ImageButton) findViewById(R.id.imageButton15);
		b16 = (ImageButton) findViewById(R.id.imageButton16);
		b17 = (ImageButton) findViewById(R.id.imageButton17);
		b18 = (ImageButton) findViewById(R.id.imageButton18);
		b19 = (ImageButton) findViewById(R.id.imageButton19);
		b20 = (ImageButton) findViewById(R.id.imageButton20);
		b21 = (ImageButton) findViewById(R.id.imageButton21);
		b22 = (ImageButton) findViewById(R.id.imageButton22);
		b23 = (ImageButton) findViewById(R.id.imageButton23);
		b24 = (ImageButton) findViewById(R.id.imageButton24);
		b0 = (ImageButton) findViewById(R.id.imageButton0);

		tile1 = 1;
		tile2 = 2;
		tile3 = 3;
		tile4 = 4;
		tile5 = 5;
		tile6 = 6;
		tile7 = 7;
		tile8 = 8;
		tile9 = 9;
		tile10 = 10;
		tile11 = 11;
		tile12 = 12;
		tile13 = 13;
		tile14 = 14;
		tile15 = 15;
		tile16 = 16;
		tile17 = 17;
		tile18 = 18;
		tile19 = 19;
		tile20 = 20;
		tile21 = 21;
		tile22 = 22;
		tile23 = 23;
		tile24 = 24;
		tile0 = 0;

		scramble();

		setImages();

		moves = 0;
		startTime = SystemClock.uptimeMillis(); //Code na ngai
		//Log.d("ERR NINI","il est : "+startTime);//Code na ngai

		customHandler.postDelayed(updateTimerThread, 0);


		//customHandler.postDelayed(updateTimerThread, 0);//Code na ngai

		tv_moves.setText("Best: " + best + "    Moves: " + (maxNumberOfMoves-moves));//Code ya kala
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
		b9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b9action();
				setImages();
			}
		});
		b10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b10action();
				setImages();
			}
		});
		b11.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b11action();
				setImages();
			}
		});
		b12.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b12action();
				setImages();
			}
		});
		b13.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b13action();
				setImages();
			}
		});
		b14.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b14action();
				setImages();
			}
		});
		b15.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b15action();
				setImages();
			}
		});
		b16.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b16action();
				setImages();
			}
		});
		b17.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b17action();
				setImages();
			}
		});
		b18.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b18action();
				setImages();
			}
		});
		b19.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b19action();
				setImages();
			}
		});
		b20.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b20action();
				setImages();
			}
		});
		b21.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b21action();
				setImages();
			}
		});
		b22.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b22action();
				setImages();
			}
		});
		b23.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b23action();
				setImages();
			}
		});
		b24.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				b24action();
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
		if (tile6 == 0) {
			tile6 = tile1;
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
		if (tile7 == 0) {
			tile7 = tile2;
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
		if (tile4 == 0) {
			tile4 = tile3;
			tile3 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile3;
			tile3 = 0;
			changed = true;
		}
	}

	public void b4action() {
		if (tile3 == 0) {
			tile3 = tile4;
			tile4 = 0;
			changed = true;
		}
		if (tile5 == 0) {
			tile5 = tile4;
			tile4 = 0;
			changed = true;
		}
		if (tile9 == 0) {
			tile9 = tile4;
			tile4 = 0;
			changed = true;
		}
	}

	public void b5action() {
		if (tile4 == 0) {
			tile4 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile10 == 0) {
			tile10 = tile5;
			tile5 = 0;
			changed = true;
		}
	}

	public void b6action() {
		if (tile1 == 0) {
			tile1 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile11 == 0) {
			tile11 = tile6;
			tile6 = 0;
			changed = true;
		}
	}

	public void b7action() {
		if (tile2 == 0) {
			tile2 = tile7;
			tile7 = 0;
			changed = true;
		}
		if (tile6 == 0) {
			tile6 = tile7;
			tile7 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile7;
			tile7 = 0;
			changed = true;
		}
		if (tile12 == 0) {
			tile12 = tile7;
			tile7 = 0;
			changed = true;
		}
	}

	public void b8action() {
		if (tile3 == 0) {
			tile3 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile9 == 0) {
			tile9 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile13 == 0) {
			tile13 = tile8;
			tile8 = 0;
			changed = true;
		}
	}

	public void b9action() {
		if (tile4 == 0) {
			tile4 = tile9;
			tile9 = 0;
			changed = true;
		}
		if (tile8 == 0) {
			tile8 = tile9;
			tile9 = 0;
			changed = true;
		}
		if (tile10 == 0) {
			tile10 = tile9;
			tile9 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile9;
			tile9 = 0;
			changed = true;
		}
	}

	public void b10action() {
		if (tile5 == 0) {
			tile5 = tile10;
			tile10 = 0;
			changed = true;
		}
		if (tile9 == 0) {
			tile9 = tile10;
			tile10 = 0;
			changed = true;
		}
		if (tile15 == 0) {
			tile15 = tile10;
			tile10 = 0;
			changed = true;
		}
	}

	public void b11action() {
		if (tile6 == 0) {
			tile6 = tile11;
			tile11 = 0;
			changed = true;
		}
		if (tile12 == 0) {
			tile12 = tile11;
			tile11 = 0;
			changed = true;
		}
		if (tile16 == 0) {
			tile16 = tile11;
			tile11 = 0;
			changed = true;
		}
	}

	public void b12action() {
		if (tile7 == 0) {
			tile7 = tile12;
			tile12 = 0;
			changed = true;
		}
		if (tile11 == 0) {
			tile11 = tile12;
			tile12 = 0;
			changed = true;
		}
		if (tile13 == 0) {
			tile13 = tile12;
			tile12 = 0;
			changed = true;
		}
		if (tile17 == 0) {
			tile17 = tile12;
			tile12 = 0;
			changed = true;
		}
	}

	public void b13action() {
		if (tile8 == 0) {
			tile8 = tile13;
			tile13 = 0;
			changed = true;
		}
		if (tile12 == 0) {
			tile12 = tile13;
			tile13 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile13;
			tile13 = 0;
			changed = true;
		}
		if (tile18 == 0) {
			tile18 = tile13;
			tile13 = 0;
			changed = true;
		}
	}

	public void b14action() {
		if (tile9 == 0) {
			tile9 = tile14;
			tile14 = 0;
			changed = true;
		}
		if (tile13 == 0) {
			tile13 = tile14;
			tile14 = 0;
			changed = true;
		}
		if (tile15 == 0) {
			tile15 = tile14;
			tile14 = 0;
			changed = true;
		}
		if (tile19 == 0) {
			tile19 = tile14;
			tile14 = 0;
			changed = true;
		}
	}

	public void b15action() {
		if (tile10 == 0) {
			tile10 = tile15;
			tile15 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile15;
			tile15 = 0;
			changed = true;
		}
		if (tile20 == 0) {
			tile20 = tile15;
			tile15 = 0;
			changed = true;
		}
	}

	public void b16action() {
		if (tile11 == 0) {
			tile11 = tile16;
			tile16 = 0;
			changed = true;
		}
		if (tile17 == 0) {
			tile17 = tile16;
			tile16 = 0;
			changed = true;
		}
		if (tile21 == 0) {
			tile21 = tile16;
			tile16 = 0;
			changed = true;
		}
	}

	public void b17action() {
		if (tile12 == 0) {
			tile12 = tile17;
			tile17 = 0;
			changed = true;
		}
		if (tile16 == 0) {
			tile16 = tile17;
			tile17 = 0;
			changed = true;
		}
		if (tile18 == 0) {
			tile18 = tile17;
			tile17 = 0;
			changed = true;
		}
		if (tile22 == 0) {
			tile22 = tile17;
			tile17 = 0;
			changed = true;
		}
	}

	public void b18action() {
		if (tile13 == 0) {
			tile13 = tile18;
			tile18 = 0;
			changed = true;
		}
		if (tile17 == 0) {
			tile17 = tile18;
			tile18 = 0;
			changed = true;
		}
		if (tile19 == 0) {
			tile19 = tile18;
			tile18 = 0;
			changed = true;
		}
		if (tile23 == 0) {
			tile23 = tile18;
			tile18 = 0;
			changed = true;
		}
	}

	public void b19action() {
		if (tile14 == 0) {
			tile14 = tile19;
			tile19 = 0;
			changed = true;
		}
		if (tile18 == 0) {
			tile18 = tile19;
			tile19 = 0;
			changed = true;
		}
		if (tile20 == 0) {
			tile20 = tile19;
			tile19 = 0;
			changed = true;
		}
		if (tile24 == 0) {
			tile24 = tile19;
			tile19 = 0;
			changed = true;
		}
	}

	public void b20action() {
		if (tile15 == 0) {
			tile15 = tile20;
			tile20 = 0;
			changed = true;
		}
		if (tile19 == 0) {
			tile19 = tile20;
			tile20 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile20;
			tile20 = 0;
			changed = true;
		}
	}

	public void b21action() {
		if (tile16 == 0) {
			tile16 = tile21;
			tile21 = 0;
			changed = true;
		}
		if (tile22 == 0) {
			tile22 = tile21;
			tile21 = 0;
			changed = true;
		}
	}

	public void b22action() {
		if (tile17 == 0) {
			tile17 = tile22;
			tile22 = 0;
			changed = true;
		}
		if (tile21 == 0) {
			tile21 = tile22;
			tile22 = 0;
			changed = true;
		}
		if (tile23 == 0) {
			tile23 = tile22;
			tile22 = 0;
			changed = true;
		}
	}

	public void b23action() {
		if (tile18 == 0) {
			tile18 = tile23;
			tile23 = 0;
			changed = true;
		}
		if (tile22 == 0) {
			tile22 = tile23;
			tile23 = 0;
			changed = true;
		}
		if (tile24 == 0) {
			tile24 = tile23;
			tile23 = 0;
			changed = true;
		}
	}

	public void b24action() {
		if (tile19 == 0) {
			tile19 = tile24;
			tile24 = 0;
			changed = true;
		}
		if (tile23 == 0) {
			tile23 = tile24;
			tile24 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile24;
			tile24 = 0;
			changed = true;
		}
	}

	public void b0action() {
		if (tile20 == 0) {
			tile20 = tile0;
			tile0 = 0;
			changed = true;
		}
		if (tile24 == 0) {
			tile24 = tile0;
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
		} else if (tile1 == 9) {
			b1.setImageResource(im9);
		} else if (tile1 == 10) {
			b1.setImageResource(im10);
		} else if (tile1 == 11) {
			b1.setImageResource(im11);
		} else if (tile1 == 12) {
			b1.setImageResource(im12);
		} else if (tile1 == 13) {
			b1.setImageResource(im13);
		} else if (tile1 == 14) {
			b1.setImageResource(im14);
		} else if (tile1 == 15) {
			b1.setImageResource(im15);
		} else if (tile1 == 16) {
			b1.setImageResource(im16);
		} else if (tile1 == 17) {
			b1.setImageResource(im17);
		} else if (tile1 == 18) {
			b1.setImageResource(im18);
		} else if (tile1 == 19) {
			b1.setImageResource(im19);
		} else if (tile1 == 20) {
			b1.setImageResource(im20);
		} else if (tile1 == 21) {
			b1.setImageResource(im21);
		} else if (tile1 == 22) {
			b1.setImageResource(im22);
		} else if (tile1 == 23) {
			b1.setImageResource(im23);
		} else if (tile1 == 24) {
			b1.setImageResource(im24);
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
		} else if (tile2 == 9) {
			b2.setImageResource(im9);
		} else if (tile2 == 10) {
			b2.setImageResource(im10);
		} else if (tile2 == 11) {
			b2.setImageResource(im11);
		} else if (tile2 == 12) {
			b2.setImageResource(im12);
		} else if (tile2 == 13) {
			b2.setImageResource(im13);
		} else if (tile2 == 14) {
			b2.setImageResource(im14);
		} else if (tile2 == 15) {
			b2.setImageResource(im15);
		} else if (tile2 == 16) {
			b2.setImageResource(im16);
		} else if (tile2 == 17) {
			b2.setImageResource(im17);
		} else if (tile2 == 18) {
			b2.setImageResource(im18);
		} else if (tile2 == 19) {
			b2.setImageResource(im19);
		} else if (tile2 == 20) {
			b2.setImageResource(im20);
		} else if (tile2 == 21) {
			b2.setImageResource(im21);
		} else if (tile2 == 22) {
			b2.setImageResource(im22);
		} else if (tile2 == 23) {
			b2.setImageResource(im23);
		} else if (tile2 == 24) {
			b2.setImageResource(im24);
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
		} else if (tile3 == 9) {
			b3.setImageResource(im9);
		} else if (tile3 == 10) {
			b3.setImageResource(im10);
		} else if (tile3 == 11) {
			b3.setImageResource(im11);
		} else if (tile3 == 12) {
			b3.setImageResource(im12);
		} else if (tile3 == 13) {
			b3.setImageResource(im13);
		} else if (tile3 == 14) {
			b3.setImageResource(im14);
		} else if (tile3 == 15) {
			b3.setImageResource(im15);
		} else if (tile3 == 16) {
			b3.setImageResource(im16);
		} else if (tile3 == 17) {
			b3.setImageResource(im17);
		} else if (tile3 == 18) {
			b3.setImageResource(im18);
		} else if (tile3 == 19) {
			b3.setImageResource(im19);
		} else if (tile3 == 20) {
			b3.setImageResource(im20);
		} else if (tile3 == 21) {
			b3.setImageResource(im21);
		} else if (tile3 == 22) {
			b3.setImageResource(im22);
		} else if (tile3 == 23) {
			b3.setImageResource(im23);
		} else if (tile3 == 24) {
			b3.setImageResource(im24);
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
		} else if (tile4 == 9) {
			b4.setImageResource(im9);
		} else if (tile4 == 10) {
			b4.setImageResource(im10);
		} else if (tile4 == 11) {
			b4.setImageResource(im11);
		} else if (tile4 == 12) {
			b4.setImageResource(im12);
		} else if (tile4 == 13) {
			b4.setImageResource(im13);
		} else if (tile4 == 14) {
			b4.setImageResource(im14);
		} else if (tile4 == 15) {
			b4.setImageResource(im15);
		} else if (tile4 == 16) {
			b4.setImageResource(im16);
		} else if (tile4 == 17) {
			b4.setImageResource(im17);
		} else if (tile4 == 18) {
			b4.setImageResource(im18);
		} else if (tile4 == 19) {
			b4.setImageResource(im19);
		} else if (tile4 == 20) {
			b4.setImageResource(im20);
		} else if (tile4 == 21) {
			b4.setImageResource(im21);
		} else if (tile4 == 22) {
			b4.setImageResource(im22);
		} else if (tile4 == 23) {
			b4.setImageResource(im23);
		} else if (tile4 == 24) {
			b4.setImageResource(im24);
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
		} else if (tile5 == 9) {
			b5.setImageResource(im9);
		} else if (tile5 == 10) {
			b5.setImageResource(im10);
		} else if (tile5 == 11) {
			b5.setImageResource(im11);
		} else if (tile5 == 12) {
			b5.setImageResource(im12);
		} else if (tile5 == 13) {
			b5.setImageResource(im13);
		} else if (tile5 == 14) {
			b5.setImageResource(im14);
		} else if (tile5 == 15) {
			b5.setImageResource(im15);
		} else if (tile5 == 16) {
			b5.setImageResource(im16);
		} else if (tile5 == 17) {
			b5.setImageResource(im17);
		} else if (tile5 == 18) {
			b5.setImageResource(im18);
		} else if (tile5 == 19) {
			b5.setImageResource(im19);
		} else if (tile5 == 20) {
			b5.setImageResource(im20);
		} else if (tile5 == 21) {
			b5.setImageResource(im21);
		} else if (tile5 == 22) {
			b5.setImageResource(im22);
		} else if (tile5 == 23) {
			b5.setImageResource(im23);
		} else if (tile5 == 24) {
			b5.setImageResource(im24);
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
		} else if (tile6 == 9) {
			b6.setImageResource(im9);
		} else if (tile6 == 10) {
			b6.setImageResource(im10);
		} else if (tile6 == 11) {
			b6.setImageResource(im11);
		} else if (tile6 == 12) {
			b6.setImageResource(im12);
		} else if (tile6 == 13) {
			b6.setImageResource(im13);
		} else if (tile6 == 14) {
			b6.setImageResource(im14);
		} else if (tile6 == 15) {
			b6.setImageResource(im15);
		} else if (tile6 == 16) {
			b6.setImageResource(im16);
		} else if (tile6 == 17) {
			b6.setImageResource(im17);
		} else if (tile6 == 18) {
			b6.setImageResource(im18);
		} else if (tile6 == 19) {
			b6.setImageResource(im19);
		} else if (tile6 == 20) {
			b6.setImageResource(im20);
		} else if (tile6 == 21) {
			b6.setImageResource(im21);
		} else if (tile6 == 22) {
			b6.setImageResource(im22);
		} else if (tile6 == 23) {
			b6.setImageResource(im23);
		} else if (tile6 == 24) {
			b6.setImageResource(im24);
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
		} else if (tile7 == 9) {
			b7.setImageResource(im9);
		} else if (tile7 == 10) {
			b7.setImageResource(im10);
		} else if (tile7 == 11) {
			b7.setImageResource(im11);
		} else if (tile7 == 12) {
			b7.setImageResource(im12);
		} else if (tile7 == 13) {
			b7.setImageResource(im13);
		} else if (tile7 == 14) {
			b7.setImageResource(im14);
		} else if (tile7 == 15) {
			b7.setImageResource(im15);
		} else if (tile7 == 16) {
			b7.setImageResource(im16);
		} else if (tile7 == 17) {
			b7.setImageResource(im17);
		} else if (tile7 == 18) {
			b7.setImageResource(im18);
		} else if (tile7 == 19) {
			b7.setImageResource(im19);
		} else if (tile7 == 20) {
			b7.setImageResource(im20);
		} else if (tile7 == 21) {
			b7.setImageResource(im21);
		} else if (tile7 == 22) {
			b7.setImageResource(im22);
		} else if (tile7 == 23) {
			b7.setImageResource(im23);
		} else if (tile7 == 24) {
			b7.setImageResource(im24);
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
		} else if (tile8 == 9) {
			b8.setImageResource(im9);
		} else if (tile8 == 10) {
			b8.setImageResource(im10);
		} else if (tile8 == 11) {
			b8.setImageResource(im11);
		} else if (tile8 == 12) {
			b8.setImageResource(im12);
		} else if (tile8 == 13) {
			b8.setImageResource(im13);
		} else if (tile8 == 14) {
			b8.setImageResource(im14);
		} else if (tile8 == 15) {
			b8.setImageResource(im15);
		} else if (tile8 == 16) {
			b8.setImageResource(im16);
		} else if (tile8 == 17) {
			b8.setImageResource(im17);
		} else if (tile8 == 18) {
			b8.setImageResource(im18);
		} else if (tile8 == 19) {
			b8.setImageResource(im19);
		} else if (tile8 == 20) {
			b8.setImageResource(im20);
		} else if (tile8 == 21) {
			b8.setImageResource(im21);
		} else if (tile8 == 22) {
			b8.setImageResource(im22);
		} else if (tile8 == 23) {
			b8.setImageResource(im23);
		} else if (tile8 == 24) {
			b8.setImageResource(im24);
		} else if (tile8 == 0) {
			b8.setImageResource(im0);
		}

		if (tile9 == 1) {
			b9.setImageResource(im1);
		} else if (tile9 == 2) {
			b9.setImageResource(im2);
		} else if (tile9 == 3) {
			b9.setImageResource(im3);
		} else if (tile9 == 4) {
			b9.setImageResource(im4);
		} else if (tile9 == 5) {
			b9.setImageResource(im5);
		} else if (tile9 == 6) {
			b9.setImageResource(im6);
		} else if (tile9 == 7) {
			b9.setImageResource(im7);
		} else if (tile9 == 8) {
			b9.setImageResource(im8);
		} else if (tile9 == 9) {
			b9.setImageResource(im9);
		} else if (tile9 == 10) {
			b9.setImageResource(im10);
		} else if (tile9 == 11) {
			b9.setImageResource(im11);
		} else if (tile9 == 12) {
			b9.setImageResource(im12);
		} else if (tile9 == 13) {
			b9.setImageResource(im13);
		} else if (tile9 == 14) {
			b9.setImageResource(im14);
		} else if (tile9 == 15) {
			b9.setImageResource(im15);
		} else if (tile9 == 16) {
			b9.setImageResource(im16);
		} else if (tile9 == 17) {
			b9.setImageResource(im17);
		} else if (tile9 == 18) {
			b9.setImageResource(im18);
		} else if (tile9 == 19) {
			b9.setImageResource(im19);
		} else if (tile9 == 20) {
			b9.setImageResource(im20);
		} else if (tile9 == 21) {
			b9.setImageResource(im21);
		} else if (tile9 == 22) {
			b9.setImageResource(im22);
		} else if (tile9 == 23) {
			b9.setImageResource(im23);
		} else if (tile9 == 24) {
			b9.setImageResource(im24);
		} else if (tile9 == 0) {
			b9.setImageResource(im0);
		}

		if (tile10 == 1) {
			b10.setImageResource(im1);
		} else if (tile10 == 2) {
			b10.setImageResource(im2);
		} else if (tile10 == 3) {
			b10.setImageResource(im3);
		} else if (tile10 == 4) {
			b10.setImageResource(im4);
		} else if (tile10 == 5) {
			b10.setImageResource(im5);
		} else if (tile10 == 6) {
			b10.setImageResource(im6);
		} else if (tile10 == 7) {
			b10.setImageResource(im7);
		} else if (tile10 == 8) {
			b10.setImageResource(im8);
		} else if (tile10 == 9) {
			b10.setImageResource(im9);
		} else if (tile10 == 10) {
			b10.setImageResource(im10);
		} else if (tile10 == 11) {
			b10.setImageResource(im11);
		} else if (tile10 == 12) {
			b10.setImageResource(im12);
		} else if (tile10 == 13) {
			b10.setImageResource(im13);
		} else if (tile10 == 14) {
			b10.setImageResource(im14);
		} else if (tile10 == 15) {
			b10.setImageResource(im15);
		} else if (tile10 == 16) {
			b10.setImageResource(im16);
		} else if (tile10 == 17) {
			b10.setImageResource(im17);
		} else if (tile10 == 18) {
			b10.setImageResource(im18);
		} else if (tile10 == 19) {
			b10.setImageResource(im19);
		} else if (tile10 == 20) {
			b10.setImageResource(im20);
		} else if (tile10 == 21) {
			b10.setImageResource(im21);
		} else if (tile10 == 22) {
			b10.setImageResource(im22);
		} else if (tile10 == 23) {
			b10.setImageResource(im23);
		} else if (tile10 == 24) {
			b10.setImageResource(im24);
		} else if (tile10 == 0) {
			b10.setImageResource(im0);
		}

		if (tile11 == 1) {
			b11.setImageResource(im1);
		} else if (tile11 == 2) {
			b11.setImageResource(im2);
		} else if (tile11 == 3) {
			b11.setImageResource(im3);
		} else if (tile11 == 4) {
			b11.setImageResource(im4);
		} else if (tile11 == 5) {
			b11.setImageResource(im5);
		} else if (tile11 == 6) {
			b11.setImageResource(im6);
		} else if (tile11 == 7) {
			b11.setImageResource(im7);
		} else if (tile11 == 8) {
			b11.setImageResource(im8);
		} else if (tile11 == 9) {
			b11.setImageResource(im9);
		} else if (tile11 == 10) {
			b11.setImageResource(im10);
		} else if (tile11 == 11) {
			b11.setImageResource(im11);
		} else if (tile11 == 12) {
			b11.setImageResource(im12);
		} else if (tile11 == 13) {
			b11.setImageResource(im13);
		} else if (tile11 == 14) {
			b11.setImageResource(im14);
		} else if (tile11 == 15) {
			b11.setImageResource(im15);
		} else if (tile11 == 16) {
			b11.setImageResource(im16);
		} else if (tile11 == 17) {
			b11.setImageResource(im17);
		} else if (tile11 == 18) {
			b11.setImageResource(im18);
		} else if (tile11 == 19) {
			b11.setImageResource(im19);
		} else if (tile11 == 20) {
			b11.setImageResource(im20);
		} else if (tile11 == 21) {
			b11.setImageResource(im21);
		} else if (tile11 == 22) {
			b11.setImageResource(im22);
		} else if (tile11 == 23) {
			b11.setImageResource(im23);
		} else if (tile11 == 24) {
			b11.setImageResource(im24);
		} else if (tile11 == 0) {
			b11.setImageResource(im0);
		}

		if (tile12 == 1) {
			b12.setImageResource(im1);
		} else if (tile12 == 2) {
			b12.setImageResource(im2);
		} else if (tile12 == 3) {
			b12.setImageResource(im3);
		} else if (tile12 == 4) {
			b12.setImageResource(im4);
		} else if (tile12 == 5) {
			b12.setImageResource(im5);
		} else if (tile12 == 6) {
			b12.setImageResource(im6);
		} else if (tile12 == 7) {
			b12.setImageResource(im7);
		} else if (tile12 == 8) {
			b12.setImageResource(im8);
		} else if (tile12 == 9) {
			b12.setImageResource(im9);
		} else if (tile12 == 10) {
			b12.setImageResource(im10);
		} else if (tile12 == 11) {
			b12.setImageResource(im11);
		} else if (tile12 == 12) {
			b12.setImageResource(im12);
		} else if (tile12 == 13) {
			b12.setImageResource(im13);
		} else if (tile12 == 14) {
			b12.setImageResource(im14);
		} else if (tile12 == 15) {
			b12.setImageResource(im15);
		} else if (tile12 == 16) {
			b12.setImageResource(im16);
		} else if (tile12 == 17) {
			b12.setImageResource(im17);
		} else if (tile12 == 18) {
			b12.setImageResource(im18);
		} else if (tile12 == 19) {
			b12.setImageResource(im19);
		} else if (tile12 == 20) {
			b12.setImageResource(im20);
		} else if (tile12 == 21) {
			b12.setImageResource(im21);
		} else if (tile12 == 22) {
			b12.setImageResource(im22);
		} else if (tile12 == 23) {
			b12.setImageResource(im23);
		} else if (tile12 == 24) {
			b12.setImageResource(im24);
		} else if (tile12 == 0) {
			b12.setImageResource(im0);
		}

		if (tile13 == 1) {
			b13.setImageResource(im1);
		} else if (tile13 == 2) {
			b13.setImageResource(im2);
		} else if (tile13 == 3) {
			b13.setImageResource(im3);
		} else if (tile13 == 4) {
			b13.setImageResource(im4);
		} else if (tile13 == 5) {
			b13.setImageResource(im5);
		} else if (tile13 == 6) {
			b13.setImageResource(im6);
		} else if (tile13 == 7) {
			b13.setImageResource(im7);
		} else if (tile13 == 8) {
			b13.setImageResource(im8);
		} else if (tile13 == 9) {
			b13.setImageResource(im9);
		} else if (tile13 == 10) {
			b13.setImageResource(im10);
		} else if (tile13 == 11) {
			b13.setImageResource(im11);
		} else if (tile13 == 12) {
			b13.setImageResource(im12);
		} else if (tile13 == 13) {
			b13.setImageResource(im13);
		} else if (tile13 == 14) {
			b13.setImageResource(im14);
		} else if (tile13 == 15) {
			b13.setImageResource(im15);
		} else if (tile13 == 16) {
			b13.setImageResource(im16);
		} else if (tile13 == 17) {
			b13.setImageResource(im17);
		} else if (tile13 == 18) {
			b13.setImageResource(im18);
		} else if (tile13 == 19) {
			b13.setImageResource(im19);
		} else if (tile13 == 20) {
			b13.setImageResource(im20);
		} else if (tile13 == 21) {
			b13.setImageResource(im21);
		} else if (tile13 == 22) {
			b13.setImageResource(im22);
		} else if (tile13 == 23) {
			b13.setImageResource(im23);
		} else if (tile13 == 24) {
			b13.setImageResource(im24);
		} else if (tile13 == 0) {
			b13.setImageResource(im0);
		}

		if (tile14 == 1) {
			b14.setImageResource(im1);
		} else if (tile14 == 2) {
			b14.setImageResource(im2);
		} else if (tile14 == 3) {
			b14.setImageResource(im3);
		} else if (tile14 == 4) {
			b14.setImageResource(im4);
		} else if (tile14 == 5) {
			b14.setImageResource(im5);
		} else if (tile14 == 6) {
			b14.setImageResource(im6);
		} else if (tile14 == 7) {
			b14.setImageResource(im7);
		} else if (tile14 == 8) {
			b14.setImageResource(im8);
		} else if (tile14 == 9) {
			b14.setImageResource(im9);
		} else if (tile14 == 10) {
			b14.setImageResource(im10);
		} else if (tile14 == 11) {
			b14.setImageResource(im11);
		} else if (tile14 == 12) {
			b14.setImageResource(im12);
		} else if (tile14 == 13) {
			b14.setImageResource(im13);
		} else if (tile14 == 14) {
			b14.setImageResource(im14);
		} else if (tile14 == 15) {
			b14.setImageResource(im15);
		} else if (tile14 == 16) {
			b14.setImageResource(im16);
		} else if (tile14 == 17) {
			b14.setImageResource(im17);
		} else if (tile14 == 18) {
			b14.setImageResource(im18);
		} else if (tile14 == 19) {
			b14.setImageResource(im19);
		} else if (tile14 == 20) {
			b14.setImageResource(im20);
		} else if (tile14 == 21) {
			b14.setImageResource(im21);
		} else if (tile14 == 22) {
			b14.setImageResource(im22);
		} else if (tile14 == 23) {
			b14.setImageResource(im23);
		} else if (tile14 == 24) {
			b14.setImageResource(im24);
		} else if (tile14 == 0) {
			b14.setImageResource(im0);
		}

		if (tile15 == 1) {
			b15.setImageResource(im1);
		} else if (tile15 == 2) {
			b15.setImageResource(im2);
		} else if (tile15 == 3) {
			b15.setImageResource(im3);
		} else if (tile15 == 4) {
			b15.setImageResource(im4);
		} else if (tile15 == 5) {
			b15.setImageResource(im5);
		} else if (tile15 == 6) {
			b15.setImageResource(im6);
		} else if (tile15 == 7) {
			b15.setImageResource(im7);
		} else if (tile15 == 8) {
			b15.setImageResource(im8);
		} else if (tile15 == 9) {
			b15.setImageResource(im9);
		} else if (tile15 == 10) {
			b15.setImageResource(im10);
		} else if (tile15 == 11) {
			b15.setImageResource(im11);
		} else if (tile15 == 12) {
			b15.setImageResource(im12);
		} else if (tile15 == 13) {
			b15.setImageResource(im13);
		} else if (tile15 == 14) {
			b15.setImageResource(im14);
		} else if (tile15 == 15) {
			b15.setImageResource(im15);
		} else if (tile15 == 16) {
			b15.setImageResource(im16);
		} else if (tile15 == 17) {
			b15.setImageResource(im17);
		} else if (tile15 == 18) {
			b15.setImageResource(im18);
		} else if (tile15 == 19) {
			b15.setImageResource(im19);
		} else if (tile15 == 20) {
			b15.setImageResource(im20);
		} else if (tile15 == 21) {
			b15.setImageResource(im21);
		} else if (tile15 == 22) {
			b15.setImageResource(im22);
		} else if (tile15 == 23) {
			b15.setImageResource(im23);
		} else if (tile15 == 24) {
			b15.setImageResource(im24);
		} else if (tile15 == 0) {
			b15.setImageResource(im0);
		}

		if (tile16 == 1) {
			b16.setImageResource(im1);
		} else if (tile16 == 2) {
			b16.setImageResource(im2);
		} else if (tile16 == 3) {
			b16.setImageResource(im3);
		} else if (tile16 == 4) {
			b16.setImageResource(im4);
		} else if (tile16 == 5) {
			b16.setImageResource(im5);
		} else if (tile16 == 6) {
			b16.setImageResource(im6);
		} else if (tile16 == 7) {
			b16.setImageResource(im7);
		} else if (tile16 == 8) {
			b16.setImageResource(im8);
		} else if (tile16 == 9) {
			b16.setImageResource(im9);
		} else if (tile16 == 10) {
			b16.setImageResource(im10);
		} else if (tile16 == 11) {
			b16.setImageResource(im11);
		} else if (tile16 == 12) {
			b16.setImageResource(im12);
		} else if (tile16 == 13) {
			b16.setImageResource(im13);
		} else if (tile16 == 14) {
			b16.setImageResource(im14);
		} else if (tile16 == 15) {
			b16.setImageResource(im15);
		} else if (tile16 == 16) {
			b16.setImageResource(im16);
		} else if (tile16 == 17) {
			b16.setImageResource(im17);
		} else if (tile16 == 18) {
			b16.setImageResource(im18);
		} else if (tile16 == 19) {
			b16.setImageResource(im19);
		} else if (tile16 == 20) {
			b16.setImageResource(im20);
		} else if (tile16 == 21) {
			b16.setImageResource(im21);
		} else if (tile16 == 22) {
			b16.setImageResource(im22);
		} else if (tile16 == 23) {
			b16.setImageResource(im23);
		} else if (tile16 == 24) {
			b16.setImageResource(im24);
		} else if (tile16 == 0) {
			b16.setImageResource(im0);
		}

		if (tile17 == 1) {
			b17.setImageResource(im1);
		} else if (tile17 == 2) {
			b17.setImageResource(im2);
		} else if (tile17 == 3) {
			b17.setImageResource(im3);
		} else if (tile17 == 4) {
			b17.setImageResource(im4);
		} else if (tile17 == 5) {
			b17.setImageResource(im5);
		} else if (tile17 == 6) {
			b17.setImageResource(im6);
		} else if (tile17 == 7) {
			b17.setImageResource(im7);
		} else if (tile17 == 8) {
			b17.setImageResource(im8);
		} else if (tile17 == 9) {
			b17.setImageResource(im9);
		} else if (tile17 == 10) {
			b17.setImageResource(im10);
		} else if (tile17 == 11) {
			b17.setImageResource(im11);
		} else if (tile17 == 12) {
			b17.setImageResource(im12);
		} else if (tile17 == 13) {
			b17.setImageResource(im13);
		} else if (tile17 == 14) {
			b17.setImageResource(im14);
		} else if (tile17 == 15) {
			b17.setImageResource(im15);
		} else if (tile17 == 16) {
			b17.setImageResource(im16);
		} else if (tile17 == 17) {
			b17.setImageResource(im17);
		} else if (tile17 == 18) {
			b17.setImageResource(im18);
		} else if (tile17 == 19) {
			b17.setImageResource(im19);
		} else if (tile17 == 20) {
			b17.setImageResource(im20);
		} else if (tile17 == 21) {
			b17.setImageResource(im21);
		} else if (tile17 == 22) {
			b17.setImageResource(im22);
		} else if (tile17 == 23) {
			b17.setImageResource(im23);
		} else if (tile17 == 24) {
			b17.setImageResource(im24);
		} else if (tile17 == 0) {
			b17.setImageResource(im0);
		}

		if (tile18 == 1) {
			b18.setImageResource(im1);
		} else if (tile18 == 2) {
			b18.setImageResource(im2);
		} else if (tile18 == 3) {
			b18.setImageResource(im3);
		} else if (tile18 == 4) {
			b18.setImageResource(im4);
		} else if (tile18 == 5) {
			b18.setImageResource(im5);
		} else if (tile18 == 6) {
			b18.setImageResource(im6);
		} else if (tile18 == 7) {
			b18.setImageResource(im7);
		} else if (tile18 == 8) {
			b18.setImageResource(im8);
		} else if (tile18 == 9) {
			b18.setImageResource(im9);
		} else if (tile18 == 10) {
			b18.setImageResource(im10);
		} else if (tile18 == 11) {
			b18.setImageResource(im11);
		} else if (tile18 == 12) {
			b18.setImageResource(im12);
		} else if (tile18 == 13) {
			b18.setImageResource(im13);
		} else if (tile18 == 14) {
			b18.setImageResource(im14);
		} else if (tile18 == 15) {
			b18.setImageResource(im15);
		} else if (tile18 == 16) {
			b18.setImageResource(im16);
		} else if (tile18 == 17) {
			b18.setImageResource(im17);
		} else if (tile18 == 18) {
			b18.setImageResource(im18);
		} else if (tile18 == 19) {
			b18.setImageResource(im19);
		} else if (tile18 == 20) {
			b18.setImageResource(im20);
		} else if (tile18 == 21) {
			b18.setImageResource(im21);
		} else if (tile18 == 22) {
			b18.setImageResource(im22);
		} else if (tile18 == 23) {
			b18.setImageResource(im23);
		} else if (tile18 == 24) {
			b18.setImageResource(im24);
		} else if (tile18 == 0) {
			b18.setImageResource(im0);
		}

		if (tile19 == 1) {
			b19.setImageResource(im1);
		} else if (tile19 == 2) {
			b19.setImageResource(im2);
		} else if (tile19 == 3) {
			b19.setImageResource(im3);
		} else if (tile19 == 4) {
			b19.setImageResource(im4);
		} else if (tile19 == 5) {
			b19.setImageResource(im5);
		} else if (tile19 == 6) {
			b19.setImageResource(im6);
		} else if (tile19 == 7) {
			b19.setImageResource(im7);
		} else if (tile19 == 8) {
			b19.setImageResource(im8);
		} else if (tile19 == 9) {
			b19.setImageResource(im9);
		} else if (tile19 == 10) {
			b19.setImageResource(im10);
		} else if (tile19 == 11) {
			b19.setImageResource(im11);
		} else if (tile19 == 12) {
			b19.setImageResource(im12);
		} else if (tile19 == 13) {
			b19.setImageResource(im13);
		} else if (tile19 == 14) {
			b19.setImageResource(im14);
		} else if (tile19 == 15) {
			b19.setImageResource(im15);
		} else if (tile19 == 16) {
			b19.setImageResource(im16);
		} else if (tile19 == 17) {
			b19.setImageResource(im17);
		} else if (tile19 == 18) {
			b19.setImageResource(im18);
		} else if (tile19 == 19) {
			b19.setImageResource(im19);
		} else if (tile19 == 20) {
			b19.setImageResource(im20);
		} else if (tile19 == 21) {
			b19.setImageResource(im21);
		} else if (tile19 == 22) {
			b19.setImageResource(im22);
		} else if (tile19 == 23) {
			b19.setImageResource(im23);
		} else if (tile19 == 24) {
			b19.setImageResource(im24);
		} else if (tile19 == 0) {
			b19.setImageResource(im0);
		}

		if (tile20 == 1) {
			b20.setImageResource(im1);
		} else if (tile20 == 2) {
			b20.setImageResource(im2);
		} else if (tile20 == 3) {
			b20.setImageResource(im3);
		} else if (tile20 == 4) {
			b20.setImageResource(im4);
		} else if (tile20 == 5) {
			b20.setImageResource(im5);
		} else if (tile20 == 6) {
			b20.setImageResource(im6);
		} else if (tile20 == 7) {
			b20.setImageResource(im7);
		} else if (tile20 == 8) {
			b20.setImageResource(im8);
		} else if (tile20 == 9) {
			b20.setImageResource(im9);
		} else if (tile20 == 10) {
			b20.setImageResource(im10);
		} else if (tile20 == 11) {
			b20.setImageResource(im11);
		} else if (tile20 == 12) {
			b20.setImageResource(im12);
		} else if (tile20 == 13) {
			b20.setImageResource(im13);
		} else if (tile20 == 14) {
			b20.setImageResource(im14);
		} else if (tile20 == 15) {
			b20.setImageResource(im15);
		} else if (tile20 == 16) {
			b20.setImageResource(im16);
		} else if (tile20 == 17) {
			b20.setImageResource(im17);
		} else if (tile20 == 18) {
			b20.setImageResource(im18);
		} else if (tile20 == 19) {
			b20.setImageResource(im19);
		} else if (tile20 == 20) {
			b20.setImageResource(im20);
		} else if (tile20 == 21) {
			b20.setImageResource(im21);
		} else if (tile20 == 22) {
			b20.setImageResource(im22);
		} else if (tile20 == 23) {
			b20.setImageResource(im23);
		} else if (tile20 == 24) {
			b20.setImageResource(im24);
		} else if (tile20 == 0) {
			b20.setImageResource(im0);
		}

		if (tile21 == 1) {
			b21.setImageResource(im1);
		} else if (tile21 == 2) {
			b21.setImageResource(im2);
		} else if (tile21 == 3) {
			b21.setImageResource(im3);
		} else if (tile21 == 4) {
			b21.setImageResource(im4);
		} else if (tile21 == 5) {
			b21.setImageResource(im5);
		} else if (tile21 == 6) {
			b21.setImageResource(im6);
		} else if (tile21 == 7) {
			b21.setImageResource(im7);
		} else if (tile21 == 8) {
			b21.setImageResource(im8);
		} else if (tile21 == 9) {
			b21.setImageResource(im9);
		} else if (tile21 == 10) {
			b21.setImageResource(im10);
		} else if (tile21 == 11) {
			b21.setImageResource(im11);
		} else if (tile21 == 12) {
			b21.setImageResource(im12);
		} else if (tile21 == 13) {
			b21.setImageResource(im13);
		} else if (tile21 == 14) {
			b21.setImageResource(im14);
		} else if (tile21 == 15) {
			b21.setImageResource(im15);
		} else if (tile21 == 16) {
			b21.setImageResource(im16);
		} else if (tile21 == 17) {
			b21.setImageResource(im17);
		} else if (tile21 == 18) {
			b21.setImageResource(im18);
		} else if (tile21 == 19) {
			b21.setImageResource(im19);
		} else if (tile21 == 20) {
			b21.setImageResource(im20);
		} else if (tile21 == 21) {
			b21.setImageResource(im21);
		} else if (tile21 == 22) {
			b21.setImageResource(im22);
		} else if (tile21 == 23) {
			b21.setImageResource(im23);
		} else if (tile21 == 24) {
			b21.setImageResource(im24);
		} else if (tile21 == 0) {
			b21.setImageResource(im0);
		}

		if (tile22 == 1) {
			b22.setImageResource(im1);
		} else if (tile22 == 2) {
			b22.setImageResource(im2);
		} else if (tile22 == 3) {
			b22.setImageResource(im3);
		} else if (tile22 == 4) {
			b22.setImageResource(im4);
		} else if (tile22 == 5) {
			b22.setImageResource(im5);
		} else if (tile22 == 6) {
			b22.setImageResource(im6);
		} else if (tile22 == 7) {
			b22.setImageResource(im7);
		} else if (tile22 == 8) {
			b22.setImageResource(im8);
		} else if (tile22 == 9) {
			b22.setImageResource(im9);
		} else if (tile22 == 10) {
			b22.setImageResource(im10);
		} else if (tile22 == 11) {
			b22.setImageResource(im11);
		} else if (tile22 == 12) {
			b22.setImageResource(im12);
		} else if (tile22 == 13) {
			b22.setImageResource(im13);
		} else if (tile22 == 14) {
			b22.setImageResource(im14);
		} else if (tile22 == 15) {
			b22.setImageResource(im15);
		} else if (tile22 == 16) {
			b22.setImageResource(im16);
		} else if (tile22 == 17) {
			b22.setImageResource(im17);
		} else if (tile22 == 18) {
			b22.setImageResource(im18);
		} else if (tile22 == 19) {
			b22.setImageResource(im19);
		} else if (tile22 == 20) {
			b22.setImageResource(im20);
		} else if (tile22 == 21) {
			b22.setImageResource(im21);
		} else if (tile22 == 22) {
			b22.setImageResource(im22);
		} else if (tile22 == 23) {
			b22.setImageResource(im23);
		} else if (tile22 == 24) {
			b22.setImageResource(im24);
		} else if (tile22 == 0) {
			b22.setImageResource(im0);
		}

		if (tile23 == 1) {
			b23.setImageResource(im1);
		} else if (tile23 == 2) {
			b23.setImageResource(im2);
		} else if (tile23 == 3) {
			b23.setImageResource(im3);
		} else if (tile23 == 4) {
			b23.setImageResource(im4);
		} else if (tile23 == 5) {
			b23.setImageResource(im5);
		} else if (tile23 == 6) {
			b23.setImageResource(im6);
		} else if (tile23 == 7) {
			b23.setImageResource(im7);
		} else if (tile23 == 8) {
			b23.setImageResource(im8);
		} else if (tile23 == 9) {
			b23.setImageResource(im9);
		} else if (tile23 == 10) {
			b23.setImageResource(im10);
		} else if (tile23 == 11) {
			b23.setImageResource(im11);
		} else if (tile23 == 12) {
			b23.setImageResource(im12);
		} else if (tile23 == 13) {
			b23.setImageResource(im13);
		} else if (tile23 == 14) {
			b23.setImageResource(im14);
		} else if (tile23 == 15) {
			b23.setImageResource(im15);
		} else if (tile23 == 16) {
			b23.setImageResource(im16);
		} else if (tile23 == 17) {
			b23.setImageResource(im17);
		} else if (tile23 == 18) {
			b23.setImageResource(im18);
		} else if (tile23 == 19) {
			b23.setImageResource(im19);
		} else if (tile23 == 20) {
			b23.setImageResource(im20);
		} else if (tile23 == 21) {
			b23.setImageResource(im21);
		} else if (tile23 == 22) {
			b23.setImageResource(im22);
		} else if (tile23 == 23) {
			b23.setImageResource(im23);
		} else if (tile23 == 24) {
			b23.setImageResource(im24);
		} else if (tile23 == 0) {
			b23.setImageResource(im0);
		}

		if (tile24 == 1) {
			b24.setImageResource(im1);
		} else if (tile24 == 2) {
			b24.setImageResource(im2);
		} else if (tile24 == 3) {
			b24.setImageResource(im3);
		} else if (tile24 == 4) {
			b24.setImageResource(im4);
		} else if (tile24 == 5) {
			b24.setImageResource(im5);
		} else if (tile24 == 6) {
			b24.setImageResource(im6);
		} else if (tile24 == 7) {
			b24.setImageResource(im7);
		} else if (tile24 == 8) {
			b24.setImageResource(im8);
		} else if (tile24 == 9) {
			b24.setImageResource(im9);
		} else if (tile24 == 10) {
			b24.setImageResource(im10);
		} else if (tile24 == 11) {
			b24.setImageResource(im11);
		} else if (tile24 == 12) {
			b24.setImageResource(im12);
		} else if (tile24 == 13) {
			b24.setImageResource(im13);
		} else if (tile24 == 14) {
			b24.setImageResource(im14);
		} else if (tile24 == 15) {
			b24.setImageResource(im15);
		} else if (tile24 == 16) {
			b24.setImageResource(im16);
		} else if (tile24 == 17) {
			b24.setImageResource(im17);
		} else if (tile24 == 18) {
			b24.setImageResource(im18);
		} else if (tile24 == 19) {
			b24.setImageResource(im19);
		} else if (tile24 == 20) {
			b24.setImageResource(im20);
		} else if (tile24 == 21) {
			b24.setImageResource(im21);
		} else if (tile24 == 22) {
			b24.setImageResource(im22);
		} else if (tile24 == 23) {
			b24.setImageResource(im23);
		} else if (tile24 == 24) {
			b24.setImageResource(im24);
		} else if (tile24 == 0) {
			b24.setImageResource(im0);
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
		} else if (tile0 == 9) {
			b0.setImageResource(im9);
		} else if (tile0 == 10) {
			b0.setImageResource(im10);
		} else if (tile0 == 11) {
			b0.setImageResource(im11);
		} else if (tile0 == 12) {
			b0.setImageResource(im12);
		} else if (tile0 == 13) {
			b0.setImageResource(im13);
		} else if (tile0 == 14) {
			b0.setImageResource(im14);
		} else if (tile0 == 15) {
			b0.setImageResource(im15);
		} else if (tile0 == 16) {
			b0.setImageResource(im16);
		} else if (tile0 == 17) {
			b0.setImageResource(im17);
		} else if (tile0 == 18) {
			b0.setImageResource(im18);
		} else if (tile0 == 19) {
			b0.setImageResource(im19);
		} else if (tile0 == 20) {
			b0.setImageResource(im20);
		} else if (tile0 == 21) {
			b0.setImageResource(im21);
		} else if (tile0 == 22) {
			b0.setImageResource(im22);
		} else if (tile0 == 23) {
			b0.setImageResource(im23);
		} else if (tile0 == 24) {
			b0.setImageResource(im24);
		} else if (tile0 == 0) {
			b0.setImageResource(im0);
		}

		if (changed == true) {
			moves = moves + 1;
			changed = false;
		}
		tv_moves.setText("Best: " + best + "    Moves: " + (maxNumberOfMoves-moves));

		if (tile1 == 1 && tile2 == 2 && tile3 == 3 && tile4 == 4 && tile5 == 5
				&& tile6 == 6 && tile7 == 7 && tile8 == 8 && tile9 == 9
				&& tile10 == 10 && tile11 == 11 && tile12 == 12 && tile13 == 13
				&& tile14 == 14 && tile15 == 15 && tile16 == 16 && tile17 == 17
				&& tile18 == 18 && tile19 == 19 && tile20 == 20 && tile21 == 21
				&& tile22 == 22 && tile23 == 23 && tile24 == 24) {

				SharedPreferences settings = getSharedPreferences("PREFS", 0);
				SharedPreferences.Editor editor = settings.edit();

				if (moves < best || best == 0) {

					editor.putInt("best_hard", moves);
					editor.commit();
				}



	//	if (updatedTime < best_time || best_time == 0L) {
				//SharedPreferences settings = getSharedPreferences("PREFS", 0);
				//SharedPreferences.Editor editor = settings.edit();
				editor.putInt("best_time_hard", moves);
				//editor.putLong("best_time_hard", moves);
				editor.commit();
				//	}


				/*int secs = (int) (updatedTime / 1000);

				int mins = secs / 60;

				secs = secs % 60;

				int milliseconds = (int) (updatedTime % 1000);
				String clock = String.format("%02d", mins) + ":" + String.format("%02d", secs);

				*/









			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					HardActivity.this);
			alertDialogBuilder
					.setMessage(
							"You successfully completed the puzzle in "+ formatDisplayTime(updatedTime) +" and " + moves
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
		else if(moves >= maxNumberOfMoves){//code na ngai//// 80 single-tile moves

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					HardActivity.this);
			alertDialogBuilder
					.setMessage(
							"You have failed to complete the puzzle in less than " + maxNumberOfMoves
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
	}

	public void scramble() {
		for (int i = 0; i < 100000; i++) {
			temp = r.nextInt(80);

			if (temp == 0) {
				if (tile2 == 0) {
					tile2 = tile1;
					tile1 = 0;
				}
			} else if (temp == 1) {
				if (tile6 == 0) {
					tile6 = tile1;
					tile1 = 0;
				}
			} else if (temp == 2) {
				if (tile1 == 0) {
					tile1 = tile2;
					tile2 = 0;
				}
			} else if (temp == 3) {
				if (tile3 == 0) {
					tile3 = tile2;
					tile2 = 0;
				}
			} else if (temp == 4) {
				if (tile7 == 0) {
					tile7 = tile2;
					tile2 = 0;
				}
			} else if (temp == 5) {
				if (tile2 == 0) {
					tile2 = tile3;
					tile3 = 0;
				}
			} else if (temp == 6) {
				if (tile4 == 0) {
					tile4 = tile3;
					tile3 = 0;
				}
			} else if (temp == 7) {
				if (tile8 == 0) {
					tile8 = tile3;
					tile3 = 0;
				}
			} else if (temp == 8) {
				if (tile3 == 0) {
					tile3 = tile4;
					tile4 = 0;
				}
			} else if (temp == 9) {
				if (tile5 == 0) {
					tile5 = tile4;
					tile4 = 0;
				}
			} else if (temp == 10) {
				if (tile9 == 0) {
					tile9 = tile4;
					tile4 = 0;
				}
			} else if (temp == 11) {
				if (tile4 == 0) {
					tile4 = tile5;
					tile5 = 0;
				}
			} else if (temp == 12) {
				if (tile10 == 0) {
					tile10 = tile5;
					tile5 = 0;
				}
			} else if (temp == 13) {
				if (tile1 == 0) {
					tile1 = tile6;
					tile6 = 0;
				}
			} else if (temp == 14) {
				if (tile7 == 0) {
					tile7 = tile6;
					tile6 = 0;
				}
			} else if (temp == 15) {
				if (tile11 == 0) {
					tile11 = tile6;
					tile6 = 0;
				}
			} else if (temp == 16) {
				if (tile2 == 0) {
					tile2 = tile7;
					tile7 = 0;
				}
			} else if (temp == 17) {
				if (tile6 == 0) {
					tile6 = tile7;
					tile7 = 0;
				}
			} else if (temp == 18) {
				if (tile8 == 0) {
					tile8 = tile7;
					tile7 = 0;
				}
			} else if (temp == 19) {
				if (tile12 == 0) {
					tile12 = tile7;
					tile7 = 0;
				}
			} else if (temp == 20) {
				if (tile3 == 0) {
					tile3 = tile8;
					tile8 = 0;
				}
			} else if (temp == 21) {
				if (tile7 == 0) {
					tile7 = tile8;
					tile8 = 0;
				}
			} else if (temp == 22) {
				if (tile9 == 0) {
					tile9 = tile8;
					tile8 = 0;
				}
			} else if (temp == 23) {
				if (tile13 == 0) {
					tile13 = tile8;
					tile8 = 0;
				}
			} else if (temp == 24) {
				if (tile4 == 0) {
					tile4 = tile9;
					tile9 = 0;
				}
			} else if (temp == 25) {
				if (tile8 == 0) {
					tile8 = tile9;
					tile9 = 0;
				}
			} else if (temp == 26) {
				if (tile10 == 0) {
					tile10 = tile9;
					tile9 = 0;
				}
			} else if (temp == 27) {
				if (tile14 == 0) {
					tile14 = tile9;
					tile9 = 0;
				}
			} else if (temp == 28) {
				if (tile5 == 0) {
					tile5 = tile10;
					tile10 = 0;
				}
			} else if (temp == 29) {
				if (tile9 == 0) {
					tile9 = tile10;
					tile10 = 0;
				}
			} else if (temp == 30) {
				if (tile15 == 0) {
					tile15 = tile10;
					tile10 = 0;
				}
			} else if (temp == 31) {
				if (tile6 == 0) {
					tile6 = tile11;
					tile11 = 0;
				}
			} else if (temp == 32) {
				if (tile12 == 0) {
					tile12 = tile11;
					tile11 = 0;
				}
			} else if (temp == 33) {
				if (tile16 == 0) {
					tile16 = tile11;
					tile11 = 0;
				}
			} else if (temp == 34) {
				if (tile7 == 0) {
					tile7 = tile12;
					tile12 = 0;
				}
			} else if (temp == 35) {
				if (tile11 == 0) {
					tile11 = tile12;
					tile12 = 0;
				}
			} else if (temp == 36) {
				if (tile13 == 0) {
					tile13 = tile12;
					tile12 = 0;
				}
			} else if (temp == 37) {
				if (tile17 == 0) {
					tile17 = tile12;
					tile12 = 0;
				}
			} else if (temp == 38) {
				if (tile8 == 0) {
					tile8 = tile13;
					tile13 = 0;
				}
			} else if (temp == 39) {
				if (tile12 == 0) {
					tile12 = tile13;
					tile13 = 0;
				}
			} else if (temp == 40) {
				if (tile14 == 0) {
					tile14 = tile13;
					tile13 = 0;
				}
			} else if (temp == 41) {
				if (tile18 == 0) {
					tile18 = tile13;
					tile13 = 0;
				}
			} else if (temp == 42) {
				if (tile9 == 0) {
					tile9 = tile14;
					tile14 = 0;
				}
			} else if (temp == 43) {
				if (tile13 == 0) {
					tile13 = tile14;
					tile14 = 0;
				}
			} else if (temp == 44) {
				if (tile15 == 0) {
					tile15 = tile14;
					tile14 = 0;
				}
			} else if (temp == 45) {
				if (tile19 == 0) {
					tile19 = tile14;
					tile14 = 0;
				}
			} else if (temp == 46) {
				if (tile10 == 0) {
					tile10 = tile15;
					tile15 = 0;
				}
			} else if (temp == 47) {
				if (tile14 == 0) {
					tile14 = tile15;
					tile15 = 0;
				}
			} else if (temp == 48) {
				if (tile20 == 0) {
					tile20 = tile15;
					tile15 = 0;
				}
			} else if (temp == 49) {
				if (tile11 == 0) {
					tile11 = tile16;
					tile16 = 0;
				}
			} else if (temp == 50) {
				if (tile17 == 0) {
					tile17 = tile16;
					tile16 = 0;
				}
			} else if (temp == 51) {
				if (tile21 == 0) {
					tile21 = tile16;
					tile16 = 0;
				}
			} else if (temp == 52) {
				if (tile12 == 0) {
					tile12 = tile17;
					tile17 = 0;
				}
			} else if (temp == 53) {
				if (tile16 == 0) {
					tile16 = tile17;
					tile17 = 0;
				}
			} else if (temp == 54) {
				if (tile18 == 0) {
					tile18 = tile17;
					tile17 = 0;
				}
			} else if (temp == 55) {
				if (tile22 == 0) {
					tile22 = tile17;
					tile17 = 0;
				}
			} else if (temp == 56) {
				if (tile13 == 0) {
					tile13 = tile18;
					tile18 = 0;
				}
			} else if (temp == 57) {
				if (tile17 == 0) {
					tile17 = tile18;
					tile18 = 0;
				}
			} else if (temp == 58) {
				if (tile19 == 0) {
					tile19 = tile18;
					tile18 = 0;
				}
			} else if (temp == 59) {
				if (tile23 == 0) {
					tile23 = tile18;
					tile18 = 0;
				}
			} else if (temp == 60) {
				if (tile14 == 0) {
					tile14 = tile19;
					tile19 = 0;
				}
			} else if (temp == 61) {
				if (tile18 == 0) {
					tile18 = tile19;
					tile19 = 0;
				}
			} else if (temp == 62) {
				if (tile20 == 0) {
					tile20 = tile19;
					tile19 = 0;
				}
			} else if (temp == 63) {
				if (tile24 == 0) {
					tile24 = tile19;
					tile19 = 0;
				}
			} else if (temp == 64) {
				if (tile15 == 0) {
					tile15 = tile20;
					tile20 = 0;
				}
			} else if (temp == 65) {
				if (tile19 == 0) {
					tile19 = tile20;
					tile20 = 0;
				}
			} else if (temp == 66) {
				if (tile0 == 0) {
					tile0 = tile20;
					tile20 = 0;
				}
			} else if (temp == 67) {
				if (tile16 == 0) {
					tile16 = tile21;
					tile21 = 0;
				}
			} else if (temp == 68) {
				if (tile22 == 0) {
					tile22 = tile21;
					tile21 = 0;
				}
			} else if (temp == 69) {
				if (tile17 == 0) {
					tile17 = tile22;
					tile22 = 0;
				}
			} else if (temp == 70) {
				if (tile21 == 0) {
					tile21 = tile22;
					tile22 = 0;
				}
			} else if (temp == 71) {
				if (tile23 == 0) {
					tile23 = tile22;
					tile22 = 0;
				}
			} else if (temp == 72) {
				if (tile18 == 0) {
					tile18 = tile23;
					tile23 = 0;
				}
			} else if (temp == 73) {
				if (tile22 == 0) {
					tile22 = tile23;
					tile23 = 0;
				}
			} else if (temp == 74) {
				if (tile24 == 0) {
					tile24 = tile23;
					tile23 = 0;
				}
			} else if (temp == 75) {
				if (tile19 == 0) {
					tile19 = tile24;
					tile24 = 0;
				}
			} else if (temp == 76) {
				if (tile23 == 0) {
					tile23 = tile24;
					tile24 = 0;
				}
			} else if (temp == 77) {
				if (tile0 == 0) {
					tile0 = tile24;
					tile24 = 0;
				}
			} else if (temp == 78) {
				if (tile20 == 0) {
					tile20 = tile0;
					tile0 = 0;
				}
			} else if (temp == 79) {
				if (tile24 == 0) {
					tile24 = tile0;
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

	private String formatDisplayTime(long timeInMilliseconds){

		int secs = (int) (timeInMilliseconds / 1000);

		int mins = secs / 60;

		secs = secs % 60;

		int milliseconds = (int) (timeInMilliseconds % 1000);

		String clock = String.format("%02d", mins) + ":" + String.format("%02d", secs);//+ "." + String.format("%03d", milliseconds) ;

		return clock;
	}

	private Runnable updateTimerThread = new Runnable() {//code Na ngai

		public void run() {

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime = timeSwapBuff + timeInMilliseconds;

			updatedTime = maxTimeInMillis - updatedTime;


			if(updatedTime <= 0){

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						HardActivity.this);
				alertDialogBuilder
						.setMessage(
								"You have failed to complete the puzzle in time")//+ formatDisplayTime(maxTimeInMillis))
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

				try {

					alertDialog.show();

				}catch( android.view.WindowManager.BadTokenException e){

					Log.v("L'erreur",""+e);
				}

			}
			else {

				tv_moves.setText(/*"Time: " +*/formatDisplayTime(updatedTime) + "    Moves: " + (maxNumberOfMoves - moves));
			/*timerValue.setText("" + mins + ":"

							+ String.format("%02d", secs) + ":"

							+ String.format("%03d", milliseconds));*/


				customHandler.postDelayed(this, 0);
			}


		}

	};

}
