package n.puzzle.oathisus;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

	Button b_easy, b_medium, b_hard, b_clear, b_title, b_help;

	Typeface tf;

	ProgressDialog progress;

	int best_easy, best_medium, best_hard;

	ImageView iv_arrow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		progress = new ProgressDialog(this);

		tf = Typeface.createFromAsset(getAssets(), "font.ttf");

		b_title = (Button) findViewById(R.id.b_title);
		b_title.setTypeface(tf);
		b_title.setTransformationMethod(null);

		b_help = (Button) findViewById(R.id.b_help);
		b_help.setTypeface(tf);
		b_help.setTransformationMethod(null);

		b_clear = (Button) findViewById(R.id.b_clear);
		b_clear.setTypeface(tf);
		b_clear.setTransformationMethod(null);

		b_easy = (Button) findViewById(R.id.b_easy);
		b_easy.setTypeface(tf);
		b_easy.setTransformationMethod(null);

		b_medium = (Button) findViewById(R.id.b_medium);
		b_medium.setTypeface(tf);
		b_medium.setTransformationMethod(null);

		b_hard = (Button) findViewById(R.id.b_hard);
		b_hard.setTypeface(tf);
		b_hard.setTransformationMethod(null);

		//TryingToMakeLevels
		//boolean passedMedium = true;
		SharedPreferences settings = getSharedPreferences("PREFS", 0);
		boolean completed_easy = settings.getBoolean("completed_easy", false);
		boolean completed_medium = settings.getBoolean("completed_medium", false);
		boolean completed_hard = settings.getBoolean("completed_hard", false);

        //Log.v("completed_easy","jdfuraùarera" +completed_easy);

		if(completed_easy){

            b_easy.setVisibility(View.GONE);
		    b_medium.setVisibility(View.VISIBLE);
		}

		if(completed_medium){

			b_medium.setVisibility(View.GONE);
			b_hard.setVisibility(View.VISIBLE);

		}

		if(completed_hard){

			b_easy.setVisibility(View.VISIBLE);
			b_medium.setVisibility(View.GONE);
			b_hard.setVisibility(View.GONE);

		}


		b_help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getSharedPreferences("PREFS", 0);
				best_easy = settings.getInt("best_easy", 0);
				best_medium = settings.getInt("best_medium", 0);
				best_hard = settings.getInt("best_hard", 0);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						MenuActivity.this);
				alertDialogBuilder
						.setMessage(
								"Best Easy - "
										+ best_easy
										+ "\nBest Medium - "
										+ best_medium
										+ "\nBest Hard - "
										+ best_hard
										+ "\n\nChoose difficulty and then the puzzle will be genarated. By clicking on any tile with number, it will switch places with the empty tile, if possible. Your goal is to arrange the numbers in proper order, in possibly less moves. Enjoy!")
						.setCancelable(true)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.dismiss();
									}
								})
						.setNeutralButton("RATE US",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Uri uri = Uri.parse("market://details?id="
												+ MenuActivity.this
														.getPackageName());
										Intent goToMarket = new Intent(
												Intent.ACTION_VIEW, uri);
										try {
											startActivity(goToMarket);
										} catch (ActivityNotFoundException e) {
											startActivity(new Intent(
													Intent.ACTION_VIEW,
													Uri.parse("http://play.google.com/store/apps/details?id="
															+ MenuActivity.this
																	.getPackageName())));
										}
										dialog.dismiss();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.setCanceledOnTouchOutside(true);
				alertDialog.show();
			}
		});

		b_title.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("market://details?id="
						+ MenuActivity.this.getPackageName());
				Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
				try {
					startActivity(goToMarket);
				} catch (ActivityNotFoundException e) {
					startActivity(new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("http://play.google.com/store/apps/details?id="
									+ MenuActivity.this.getPackageName())));
				}
			}
		});

		b_easy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				progress.setIndeterminate(true);
				progress.setCancelable(false);
				progress.setMessage("Shiffling puzzle...");
				progress.show();

				new Thread(new Runnable() {
					public void run() {
						progress.dismiss();
						Intent next = new Intent(getBaseContext(),
								EasyActivity.class);
						startActivity(next);
					}
				}).start();

			}
		});

		b_medium.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				progress.setIndeterminate(true);
				progress.setCancelable(false);
				progress.setMessage("Shiffling puzzle...");
				progress.show();

				new Thread(new Runnable() {
					public void run() {
						progress.dismiss();
						Intent next = new Intent(getBaseContext(),
								MediumActivity.class);
						startActivity(next);
					}
				}).start();

			}
		});

		b_hard.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				progress.setIndeterminate(true);
				progress.setCancelable(false);
				progress.setMessage("Shiffling puzzle...");
				progress.show();

				new Thread(new Runnable() {
					public void run() {
						progress.dismiss();
						Intent next = new Intent(getBaseContext(),
								HardActivity.class);
						startActivity(next);
					}
				}).start();

			}
		});

		b_clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getSharedPreferences("PREFS", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("best_easy", 0);
				editor.putBoolean("completed_easy", false); //TryingToMakeLevels

				editor.putInt("best_medium", 0);
				editor.putBoolean("completed_medium", false); //TryingToMakeLevels

				editor.putInt("best_hard", 0);
				editor.putBoolean("completed_hard", false); //TryingToMakeLevels

				editor.commit();

				Toast.makeText(MenuActivity.this, "Scores cleared!",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
