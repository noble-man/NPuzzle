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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


//LIMITER LE NOMBRE DE MOVES : OK
//PAS DE CHIFFRES SUR LES IMAGES
/*
* For the 15-puzzle, lengths of optimal solutions range from 0 to 80 single-tile moves (there are 17 configurations requiring 80 moves)
* or 43 multi-tile moves;[5]
* */

public class MediumActivity extends AppCompatActivity {

	ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14,
			b15, b0;

	TextView tv_moves;

	int tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
			tile11, tile12, tile13, tile14, tile15, tile0;

	int im1, im2, im3, im4, im5, im6, im7, im8, im9, im10, im11, im12, im13,
			im14, im15, im0;

	Random r;

	int temp;
	final static int maxNumberOfMoves = 100;

	Typeface tf;

	int moves = 0;
	int best = 0;
	boolean changed = false; // if tile is moved

	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medium);

		// Create the interstitial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-7124948529897131/7484017687");

		// Create ad request.
		AdRequest adRequest = new AdRequest.Builder().build();

		// Begin loading your interstitial.
		interstitial.loadAd(adRequest);

		SharedPreferences settings = getSharedPreferences("PREFS", 0);
		best = settings.getInt("best_medium", 0);

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
		tile0 = 0;

		scramble();

		setImages();

		moves = 0;
		tv_moves.setText("Best: " + best + "    Moves: " + (maxNumberOfMoves - moves));

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
		if (tile5 == 0) {
			tile5 = tile1;
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
		if (tile6 == 0) {
			tile6 = tile2;
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
		if (tile7 == 0) {
			tile7 = tile3;
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
		if (tile8 == 0) {
			tile8 = tile4;
			tile4 = 0;
			changed = true;
		}
	}

	public void b5action() {
		if (tile1 == 0) {
			tile1 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile6 == 0) {
			tile6 = tile5;
			tile5 = 0;
			changed = true;
		}
		if (tile9 == 0) {
			tile9 = tile5;
			tile5 = 0;
			changed = true;
		}
	}

	public void b6action() {
		if (tile2 == 0) {
			tile2 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile5 == 0) {
			tile5 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile6;
			tile6 = 0;
			changed = true;
		}
		if (tile10 == 0) {
			tile10 = tile6;
			tile6 = 0;
			changed = true;
		}
	}

	public void b7action() {
		if (tile3 == 0) {
			tile3 = tile7;
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
		if (tile11 == 0) {
			tile11 = tile7;
			tile7 = 0;
			changed = true;
		}
	}

	public void b8action() {
		if (tile4 == 0) {
			tile4 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile7 == 0) {
			tile7 = tile8;
			tile8 = 0;
			changed = true;
		}
		if (tile12 == 0) {
			tile12 = tile8;
			tile8 = 0;
			changed = true;
		}
	}

	public void b9action() {
		if (tile5 == 0) {
			tile5 = tile9;
			tile9 = 0;
			changed = true;
		}
		if (tile10 == 0) {
			tile10 = tile9;
			tile9 = 0;
			changed = true;
		}
		if (tile13 == 0) {
			tile13 = tile9;
			tile9 = 0;
			changed = true;
		}
	}

	public void b10action() {
		if (tile6 == 0) {
			tile6 = tile10;
			tile10 = 0;
			changed = true;
		}
		if (tile9 == 0) {
			tile9 = tile10;
			tile10 = 0;
			changed = true;
		}
		if (tile11 == 0) {
			tile11 = tile10;
			tile10 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile10;
			tile10 = 0;
			changed = true;
		}
	}

	public void b11action() {
		if (tile7 == 0) {
			tile7 = tile11;
			tile11 = 0;
			changed = true;
		}
		if (tile10 == 0) {
			tile10 = tile11;
			tile11 = 0;
			changed = true;
		}
		if (tile12 == 0) {
			tile12 = tile11;
			tile11 = 0;
			changed = true;
		}
		if (tile15 == 0) {
			tile15 = tile11;
			tile11 = 0;
			changed = true;
		}
	}

	public void b12action() {
		if (tile8 == 0) {
			tile8 = tile12;
			tile12 = 0;
			changed = true;
		}
		if (tile11 == 0) {
			tile11 = tile12;
			tile12 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile12;
			tile12 = 0;
			changed = true;
		}
	}

	public void b13action() {
		if (tile9 == 0) {
			tile9 = tile13;
			tile13 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile13;
			tile13 = 0;
			changed = true;
		}
	}

	public void b14action() {
		if (tile10 == 0) {
			tile10 = tile14;
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
	}

	public void b15action() {
		if (tile11 == 0) {
			tile11 = tile15;
			tile15 = 0;
			changed = true;
		}
		if (tile14 == 0) {
			tile14 = tile15;
			tile15 = 0;
			changed = true;
		}
		if (tile0 == 0) {
			tile0 = tile15;
			tile15 = 0;
			changed = true;
		}
	}

	public void b0action() {
		if (tile12 == 0) {
			tile12 = tile0;
			tile0 = 0;
			changed = true;
		}
		if (tile15 == 0) {
			tile15 = tile0;
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
		} else if (tile15 == 0) {
			b15.setImageResource(im0);
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
				&& tile14 == 14 && tile15 == 15) {

			if (moves < best || best == 0) {
				SharedPreferences settings = getSharedPreferences("PREFS", 0);
				SharedPreferences.Editor editor = settings.edit();


				editor.putBoolean("completed_easy", true);


				editor.putInt("best_medium", moves);
				editor.commit();
			}

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					MediumActivity.this);
			alertDialogBuilder
					.setMessage(
							"You successfully completed the puzzle in " + moves
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
					MediumActivity.this);
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
			temp = r.nextInt(48);

			if (temp == 0) {
				if (tile2 == 0) {
					tile2 = tile1;
					tile1 = 0;
				}
			} else if (temp == 1) {
				if (tile5 == 0) {
					tile5 = tile1;
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
				if (tile6 == 0) {
					tile6 = tile2;
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
				if (tile7 == 0) {
					tile7 = tile3;
					tile3 = 0;
				}
			} else if (temp == 8) {
				if (tile3 == 0) {
					tile3 = tile4;
					tile4 = 0;
				}
			} else if (temp == 9) {
				if (tile8 == 0) {
					tile8 = tile4;
					tile4 = 0;
				}
			} else if (temp == 10) {
				if (tile1 == 0) {
					tile1 = tile5;
					tile5 = 0;
				}
			} else if (temp == 11) {
				if (tile6 == 0) {
					tile6 = tile5;
					tile5 = 0;
				}
			} else if (temp == 12) {
				if (tile9 == 0) {
					tile9 = tile5;
					tile5 = 0;
				}
			} else if (temp == 13) {
				if (tile2 == 0) {
					tile2 = tile6;
					tile6 = 0;
				}
			} else if (temp == 14) {
				if (tile5 == 0) {
					tile5 = tile6;
					tile6 = 0;
				}
			} else if (temp == 15) {
				if (tile7 == 0) {
					tile7 = tile6;
					tile6 = 0;
				}
			} else if (temp == 16) {
				if (tile10 == 0) {
					tile10 = tile6;
					tile6 = 0;
				}
			} else if (temp == 17) {
				if (tile3 == 0) {
					tile3 = tile7;
					tile7 = 0;
				}
			} else if (temp == 18) {
				if (tile6 == 0) {
					tile6 = tile7;
					tile7 = 0;
				}
			} else if (temp == 19) {
				if (tile8 == 0) {
					tile8 = tile7;
					tile7 = 0;
				}
			} else if (temp == 20) {
				if (tile11 == 0) {
					tile11 = tile7;
					tile7 = 0;
				}
			} else if (temp == 21) {
				if (tile4 == 0) {
					tile4 = tile8;
					tile8 = 0;
				}
			} else if (temp == 22) {
				if (tile7 == 0) {
					tile7 = tile8;
					tile8 = 0;
				}
			} else if (temp == 23) {
				if (tile12 == 0) {
					tile12 = tile8;
					tile8 = 0;
				}
			} else if (temp == 24) {
				if (tile5 == 0) {
					tile5 = tile9;
					tile9 = 0;
				}
			} else if (temp == 25) {
				if (tile10 == 0) {
					tile10 = tile9;
					tile9 = 0;
				}
			} else if (temp == 26) {
				if (tile13 == 0) {
					tile13 = tile9;
					tile9 = 0;
				}
			} else if (temp == 27) {
				if (tile6 == 0) {
					tile6 = tile10;
					tile10 = 0;
				}
			} else if (temp == 28) {
				if (tile9 == 0) {
					tile9 = tile10;
					tile10 = 0;
				}
			} else if (temp == 29) {
				if (tile11 == 0) {
					tile11 = tile10;
					tile10 = 0;
				}
			} else if (temp == 30) {
				if (tile14 == 0) {
					tile14 = tile10;
					tile10 = 0;
				}
			} else if (temp == 31) {
				if (tile7 == 0) {
					tile7 = tile11;
					tile11 = 0;
				}
			} else if (temp == 32) {
				if (tile10 == 0) {
					tile10 = tile11;
					tile11 = 0;
				}
			} else if (temp == 33) {
				if (tile12 == 0) {
					tile12 = tile11;
					tile11 = 0;
				}
			} else if (temp == 34) {
				if (tile15 == 0) {
					tile15 = tile11;
					tile11 = 0;
				}
			} else if (temp == 35) {
				if (tile8 == 0) {
					tile8 = tile12;
					tile12 = 0;
				}
			} else if (temp == 36) {
				if (tile11 == 0) {
					tile11 = tile12;
					tile12 = 0;
				}
			} else if (temp == 37) {
				if (tile0 == 0) {
					tile0 = tile12;
					tile12 = 0;
				}
			} else if (temp == 38) {
				if (tile9 == 0) {
					tile9 = tile13;
					tile13 = 0;
				}
			} else if (temp == 39) {
				if (tile14 == 0) {
					tile14 = tile13;
					tile13 = 0;
				}
			} else if (temp == 40) {
				if (tile10 == 0) {
					tile10 = tile14;
					tile14 = 0;
				}
			} else if (temp == 41) {
				if (tile13 == 0) {
					tile13 = tile14;
					tile14 = 0;
				}
			} else if (temp == 42) {
				if (tile15 == 0) {
					tile15 = tile14;
					tile14 = 0;
				}
			} else if (temp == 43) {
				if (tile11 == 0) {
					tile11 = tile15;
					tile15 = 0;
				}
			} else if (temp == 44) {
				if (tile14 == 0) {
					tile14 = tile15;
					tile15 = 0;
				}
			} else if (temp == 45) {
				if (tile0 == 0) {
					tile0 = tile15;
					tile15 = 0;
				}
			} else if (temp == 46) {
				if (tile12 == 0) {
					tile12 = tile0;
					tile0 = 0;
				}
			} else if (temp == 47) {
				if (tile15 == 0) {
					tile15 = tile0;
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
}
