package another.sudoku;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import another.sudoku.helpers.Initialization;
import another.sudoku.helpers.NumbersAdapter;
import another.sudoku.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gridview)
    GridView grid;
    @BindView(R.id.frameLayout_number1)
    FrameLayout mFrameNumber1;
    @BindView(R.id.frameLayout_number2)
    FrameLayout mFrameNumber2;
    @BindView(R.id.frameLayout_number3)
    FrameLayout mFrameNumber3;
    @BindView(R.id.frameLayout_number4)
    FrameLayout mFrameNumber4;
    @BindView(R.id.frameLayout_number5)
    FrameLayout mFrameNumber5;
    @BindView(R.id.frameLayout_number6)
    FrameLayout mFrameNumber6;
    @BindView(R.id.frameLayout_number7)
    FrameLayout mFrameNumber7;
    @BindView(R.id.frameLayout_number8)
    FrameLayout mFrameNumber8;
    @BindView(R.id.frameLayout_number9)
    FrameLayout mFrameNumber9;
    @BindView(R.id.frameLayout_number0)
    FrameLayout mFrameNumber0;

    int SquaredRoot = 3;
    int difficulty = 1;

    private int[][] data = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
    private int[][] hidden = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];

    boolean isSolvable = false;
    Initialization iis = new Initialization();
    SharedPreferences sharedPref;
    private ProgressDialog mProgressDialog;
    Context context;
    ConstraintLayout.LayoutParams params;
    private NumbersAdapter na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SquaredRoot = sharedPref.getInt(getString(R.string.sudoku_root), 3);
        difficulty = sharedPref.getInt(getString(R.string.sudoku_difficulty), 1);
        data = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
        params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        grid.setNumColumns(SquaredRoot * SquaredRoot);
        context = this;
        //   grid.setOnTouchListener(new TouchHandler());
        na = new NumbersAdapter(this, data);
        grid.setAdapter(na);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                new LongOperation().execute(""));


        mFrameNumber1.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            // na.getPosition();
            na.setNum(1, (int) na.getPosition(),SquaredRoot);
        });

        mFrameNumber2.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
        na.setNum(2, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber3.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
            na.setNum(3, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber4.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
            na.setNum(4, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber5.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
            na.setNum(5, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber6.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();
            na.setNum(6, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber7.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
            na.setNum(7, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber8.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();
            na.setNum(8, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber9.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_SHORT).show();
            na.setNum(9, (int) na.getPosition(),SquaredRoot);});

        mFrameNumber0.setOnClickListener(v -> {Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
            na.setNum(0, (int) na.getPosition(),SquaredRoot);});
    }


    @SuppressLint("StaticFieldLeak")
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            data = iis.myinitializeSudokuNumbers(SquaredRoot);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            new SecondLong().execute();
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(context, "Wait", "Initializing Sudoku ...");
            mProgressDialog.setCanceledOnTouchOutside(false); // main method that force user cannot click outside
            mProgressDialog.setCancelable(true);
            mProgressDialog.setOnCancelListener(dlg -> {
            });
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class SecondLong extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            hidden = iis.hideFromBlock(difficulty, SquaredRoot, data);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            new LastLong().execute();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LastLong extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            int[][] board = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
            for (int i = 0; i < hidden.length; i++) {
                System.arraycopy(hidden[i], 0, board[i], 0, hidden.length);
            }
            isSolvable = iis.solve(board);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (isSolvable) {
                grid.setAdapter(null);
                na = new NumbersAdapter(context, hidden);
                grid.setAdapter(na);
                if (this.isCancelled()) {
                    return;
                }

                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
            } else {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                isSolvable = false;
                data = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
                hidden = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
                new LongOperation().execute();
            }
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SquaredRoot = sharedPref.getInt(getString(R.string.sudoku_root), 3);
            int checkedItem = 0;
            if (SquaredRoot == 4) {
                checkedItem = 1;
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Select sudoku size:")
                    .setSingleChoiceItems(new String[]{"Normal(9x9)", "Giga(16x16)"}, checkedItem, null)
                    .setPositiveButton("OK", (dialog, which) -> {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        if (selectedPosition == 0) {
                            SquaredRoot = 3;
                            data = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
                            grid.setAdapter(null);
                            na = new NumbersAdapter(this, data);
                            grid.setAdapter(na);
                            grid.setNumColumns(3 * 3);
                            editor.putInt(getString(R.string.sudoku_root), 3);
                        } else {
                            SquaredRoot = 4;
                            data = new int[SquaredRoot * SquaredRoot][SquaredRoot * SquaredRoot];
                            editor.putInt(getString(R.string.sudoku_root), 4);
                            grid.setAdapter(null);
                            na = new NumbersAdapter(this, data);
                            grid.setAdapter(na);
                            grid.setNumColumns(4 * 4);
                        }
                        editor.apply();
                    });
            builder.show();
            return true;
        } else if (id == R.id.action_difficulty) {
            difficulty = sharedPref.getInt(getString(R.string.sudoku_difficulty), 1);
            int checkedItem = 0;
            if (difficulty == 2) {
                checkedItem = 1;
            }
            if (difficulty == 3) {
                checkedItem = 2;
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Select difficulty:")
                    .setSingleChoiceItems(new String[]{"Easy", "Medium", "Hard"}, checkedItem, null)
                    .setPositiveButton("OK", (dialog, which) -> {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        if (selectedPosition == 0) {
                            difficulty = 1;
                            editor.putInt(getString(R.string.sudoku_difficulty), 1);
                        } else if (selectedPosition == 1) {
                            difficulty = 2;
                            editor.putInt(getString(R.string.sudoku_difficulty), 2);
                        } else if (selectedPosition == 2) {
                            difficulty = 3;
                            editor.putInt(getString(R.string.sudoku_difficulty), 3);
                        }
                        editor.apply();
                    });
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}