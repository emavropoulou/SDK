package game.sudoku.activities;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.sudoku.helpers.NumbersAdapter;
import game.sudoku.R;
import game.sudoku.services.InitializeNumbersService;
import game.sudoku.services.SetupTableService;
import game.sudoku.services.impl.SetupTableServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Views
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gridview)
    GridView table;
    @BindView(R.id.frameLayout_number1)
    FrameLayout number1;
    @BindView(R.id.frameLayout_number2)
    FrameLayout number2;
    @BindView(R.id.frameLayout_number3)
    FrameLayout number3;
    @BindView(R.id.frameLayout_number4)
    FrameLayout number4;
    @BindView(R.id.frameLayout_number5)
    FrameLayout number5;
    @BindView(R.id.frameLayout_number6)
    FrameLayout number6;
    @BindView(R.id.frameLayout_number7)
    FrameLayout number7;
    @BindView(R.id.frameLayout_number8)
    FrameLayout number8;
    @BindView(R.id.frameLayout_number9)
    FrameLayout number9;
    @BindView(R.id.frameLayout_number0)
    FrameLayout number0;

    //Initialize variables
    int defaultSquaredRoot = 3;
    int defaultDifficulty = 1;
    int defaultSquared = (int) Math.pow(defaultSquaredRoot, 2);

    private int[][] sudokuTable = new int[defaultSquared][defaultSquared];
    private int[][] hiddenNumbers = new int[defaultSquared][defaultSquared];

    private boolean isSolvable = false;

    //Initialize context
    private SetupTableService setupTableService;
    private SharedPreferences sharedPref;
    private ProgressDialog mProgressDialog;
    private Context context;
    private NumbersAdapter numbersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        setupTableService = new SetupTableServiceImpl();

        defaultSquaredRoot = sharedPref.getInt(getString(R.string.sudoku_root), 3);
        defaultDifficulty = sharedPref.getInt(getString(R.string.sudoku_difficulty), 1);
        defaultSquared = (int) Math.pow(defaultSquaredRoot, 2);
        sudokuTable = new int[defaultSquared][defaultSquared];

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        table.setNumColumns(defaultSquared);
        context = this;
        //   grid.setOnTouchListener(new TouchHandler());
        numbersAdapter = new NumbersAdapter(this, sudokuTable);
        table.setAdapter(numbersAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                new LongOperation().execute(""));


        number1.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
            // na.getPosition();
            numbersAdapter.setNum(1, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number2.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(2, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number3.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(3, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number4.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(4, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number5.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(5, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number6.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(6, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number7.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(7, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number8.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(8, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number9.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(9, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });

        number0.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
            numbersAdapter.setNum(0, (int) numbersAdapter.getPosition(), defaultSquaredRoot);
        });
    }


    @SuppressLint("StaticFieldLeak")
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            InitializeNumbersServiceImpl service = new InitializeNumbersServiceImpl();
            service.start(defaultSquaredRoot);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
          mProgressDialog.cancel();
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
            hiddenNumbers = setupTableService.hideFromBlock(defaultDifficulty, defaultSquaredRoot, sudokuTable);
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
            int[][] board = new int[defaultSquared][defaultSquared];
            for (int i = 0; i < hiddenNumbers.length; i++) {
                System.arraycopy(hiddenNumbers[i], 0, board[i], 0, hiddenNumbers.length);
            }
            isSolvable = setupTableService.isSudokuSolvable(board);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (isSolvable) {
                table.setAdapter(null);
                numbersAdapter = new NumbersAdapter(context, hiddenNumbers);
                table.setAdapter(numbersAdapter);
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
                sudokuTable = new int[defaultSquared][defaultSquared];
                hiddenNumbers = new int[defaultSquared][defaultSquared];
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
            defaultSquaredRoot = sharedPref.getInt(getString(R.string.sudoku_root), 3);
            defaultSquared = (int) Math.pow(defaultSquaredRoot, 2);
            int checkedItem = 0;
            if (defaultSquaredRoot == 4) {
                checkedItem = 1;
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Select sudoku size:")
                    .setSingleChoiceItems(new String[]{"Normal(9x9)", "Giga(16x16)"}, checkedItem, null)
                    .setPositiveButton("OK", (dialog, which) -> {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        if (selectedPosition == 0) {
                            defaultSquaredRoot = 3;
                            defaultSquared = (int) Math.pow(defaultSquaredRoot, 2);
                            sudokuTable = new int[defaultSquared][defaultSquared];
                            table.setAdapter(null);
                            numbersAdapter = new NumbersAdapter(this, sudokuTable);
                            table.setAdapter(numbersAdapter);
                            table.setNumColumns(3 * 3);
                            editor.putInt(getString(R.string.sudoku_root), 3);
                        } else {
                            defaultSquaredRoot = 4;
                            defaultSquared = (int) Math.pow(defaultSquaredRoot, 2);
                            sudokuTable = new int[defaultSquared][defaultSquared];
                            editor.putInt(getString(R.string.sudoku_root), 4);
                            table.setAdapter(null);
                            numbersAdapter = new NumbersAdapter(this, sudokuTable);
                            table.setAdapter(numbersAdapter);
                            table.setNumColumns(4 * 4);
                        }
                        editor.apply();
                    });
            builder.show();
            return true;
        } else if (id == R.id.action_difficulty) {
            defaultDifficulty = sharedPref.getInt(getString(R.string.sudoku_difficulty), 1);
            int checkedItem = 0;
            if (defaultDifficulty == 2) {
                checkedItem = 1;
            }
            if (defaultDifficulty == 3) {
                checkedItem = 2;
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Select difficulty:")
                    .setSingleChoiceItems(new String[]{"Easy", "Medium", "Hard"}, checkedItem, null)
                    .setPositiveButton("OK", (dialog, which) -> {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        if (selectedPosition == 0) {
                            defaultDifficulty = 1;
                            editor.putInt(getString(R.string.sudoku_difficulty), 1);
                        } else if (selectedPosition == 1) {
                            defaultDifficulty = 2;
                            editor.putInt(getString(R.string.sudoku_difficulty), 2);
                        } else if (selectedPosition == 2) {
                            defaultDifficulty = 3;
                            editor.putInt(getString(R.string.sudoku_difficulty), 3);
                        }
                        editor.apply();
                    });
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class InitializeNumbersServiceImpl implements Callback<int[][]> {

        static final String BASE_URL = "http://10.0.2.2:9999/";

        public void start(int root) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            InitializeNumbersService initNumbersService = retrofit.create(InitializeNumbersService.class);

            Call<int[][]> call = initNumbersService.getInitialNumbers(root);
            call.enqueue(this);

        }



        @Override
        public void onResponse(Call<int[][]> call, Response<int[][]> response) {
            if (response.isSuccessful()) {
                sudokuTable=response.body();
                new SecondLong().execute();
            } else {
                System.out.println(response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<int[][]> call, Throwable t) {
            t.printStackTrace();
        }
    }
}