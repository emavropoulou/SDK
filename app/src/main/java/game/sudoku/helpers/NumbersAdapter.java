package game.sudoku.helpers;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import game.sudoku.R;

public class NumbersAdapter extends BaseAdapter {
    private String[] tempNumbersTable;
    private Context context;

    private int[] borders;
    private List<Integer> editablePositions;
    private int rowLength;
    private int columnLength;
    private int squared;
    private long position;

    public NumbersAdapter(Context context, int[][] numbersTable) {
        position = -1;
        editablePositions = new ArrayList<>();

        int squaredRoot = (int) Math.sqrt(numbersTable.length);
        rowLength = columnLength=numbersTable.length;
        squared = (int) Math.pow(rowLength,2);
        tempNumbersTable = new String[squared];
        borders = new int[squared];
        int count = 0;
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                if (row == 0 && column % squaredRoot == 0) {
                    borders[count] = 2;
                } else if (row == 0 && column == rowLength - 1) {
                    borders[count] = 6;
                } else if (row == 0 && column != rowLength - 1 && column % squaredRoot != 0) {
                    borders[count] = 4;
                } else if (row != 0 && row % squaredRoot == squaredRoot - 1 && column % squaredRoot == 0) {
                    borders[count] = 3;
                } else if (row != 0 && row % squaredRoot == squaredRoot - 1 && column != rowLength - 1 && column % squaredRoot != 0) {
                    borders[count] = 8;
                } else if (row != 0 && row % squaredRoot == squaredRoot - 1 && column == rowLength - 1 && column % squaredRoot != 0) {
                    borders[count] = 7;
                } else if (row != 0 && (row % squaredRoot != squaredRoot - 1) && column % squaredRoot == 0) {
                    borders[count] = 1;
                } else if (row != 0 && (row % squaredRoot != squaredRoot - 1) && column == rowLength - 1) {
                    borders[count] = 5;
                } else {
                    borders[count] = 0;
                }

                if (numbersTable[row][column] != 0) {
                    this.tempNumbersTable[count] = String.valueOf(numbersTable[row][column]);
                } else {
                    this.tempNumbersTable[count] = " ";
                    this.editablePositions.add(count);
                }
                count = count + 1;
            }

        }
        this.context = context;
    }

    @Override
    public int getCount() {

        return tempNumbersTable.length;
    }

    @Override
    public Object getItem(int itemsPosition) {
        return tempNumbersTable[itemsPosition];
    }

    @Override
    public long getItemId(int itemsIdPosition) {
        return itemsIdPosition;
    }


    public long getPosition() {
        return position;
    }

    public void setNum(int num, int numPosition, int size) {

        switch (size) {
            case 3:
                if (num != 0) {
                    if (numPosition >= 0) {
                        StringBuilder s = new StringBuilder();
                        s.append(tempNumbersTable[numPosition]);
                        s.append(String.valueOf(num));
                        if (s.toString().length() > 2) {
                            s.deleteCharAt(1);
                        }
                        tempNumbersTable[numPosition] = s.toString();
                        this.notifyDataSetChanged();
                    }
                }
                break;
            case 4:
                if (numPosition >= 0) {
                    StringBuilder s = new StringBuilder();
                    s.append(tempNumbersTable[numPosition]);
                    s.append(String.valueOf(num));
                    if (s.toString().length() == 2) {
                        if (num == '0') {
                            s.deleteCharAt(1);
                        }
                    }
                    if (s.toString().length() > 2) {

                        if (!(s.charAt(1) == '1')) {
                            s.deleteCharAt(1);
                        } else {
                            if (s.length() == 4) {
                                s.deleteCharAt(1);
                                s.deleteCharAt(1);
                            }
                        }

                    }
                    tempNumbersTable[numPosition] = s.toString();
                    this.notifyDataSetChanged();
                }
                break;


        }
    }


    @Override
    public View getView(int viewPosition, View convertView, ViewGroup parent) {
        EditText text = new EditText(this.context);


        if ((position == viewPosition)) {
            text.setEnabled(true);
            text.setBackgroundColor(Color.parseColor("#ffffbb33"));
            text.setInputType(InputType.TYPE_NULL);
            text.setOnFocusChangeListener((v, event) -> {

                if (event) {
                    position = viewPosition;
                    text.setBackgroundColor(Color.GREEN);
                } else {
                    position = -1;
                    text.setBackgroundColor(Color.WHITE);
                    switch (borders[viewPosition]) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            text.setBackgroundResource(R.drawable.left_border);
                            break;
                        }
                        case 2: {
                            text.setBackgroundResource(R.drawable.left_top_border);
                            break;
                        }
                        case 3: {
                            text.setBackgroundResource(R.drawable.left_bottom_border);
                            break;
                        }
                        case 4: {
                            text.setBackgroundResource(R.drawable.top_border);
                            break;
                        }
                        case 5: {
                            text.setBackgroundResource(R.drawable.right_border);
                            break;
                        }
                        case 6: {
                            text.setBackgroundResource(R.drawable.right_top_border);
                            break;
                        }
                        case 7: {
                            text.setBackgroundResource(R.drawable.right_bottom_border);
                            break;
                        }
                        case 8: {
                            text.setBackgroundResource(R.drawable.bottom_border);
                            break;
                        }

                    }
                }
            });
            switch (borders[viewPosition]) {
                case 0: {
                    break;
                }
                case 1: {
                    text.setBackgroundResource(R.drawable.left_border_yellow);
                    break;
                }
                case 2: {
                    text.setBackgroundResource(R.drawable.left_top_border_yellow);
                    break;
                }
                case 3: {
                    text.setBackgroundResource(R.drawable.left_bottom_border_yellow);
                    break;
                }
                case 4: {
                    text.setBackgroundResource(R.drawable.top_borders_yellow);
                    break;
                }
                case 5: {
                    text.setBackgroundResource(R.drawable.right_border_yellow);
                    break;
                }
                case 6: {
                    text.setBackgroundResource(R.drawable.right_top_border_yellow);
                    break;
                }
                case 7: {
                    text.setBackgroundResource(R.drawable.right_bottom_border_yellow);
                    break;
                }
                case 8: {
                    text.setBackgroundResource(R.drawable.bottom_border_yellow);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        text.setText(tempNumbersTable[viewPosition]);
        if (tempNumbersTable[viewPosition].equals(" ") || editablePositions.contains(viewPosition)) {
            text.setEnabled(true);
            text.setBackgroundColor(Color.parseColor("#ffffbb33"));
            text.setInputType(InputType.TYPE_NULL);

            text.setOnFocusChangeListener((v, event) -> {
                if (event) {
                    position = viewPosition;
                    text.setBackgroundColor(Color.GREEN);
                } else {
                    position = -1;
                    text.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    ;
                    switch (borders[viewPosition]) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            text.setBackgroundResource(R.drawable.left_border_yellow);
                            break;
                        }
                        case 2: {
                            text.setBackgroundResource(R.drawable.left_top_border_yellow);
                            break;
                        }
                        case 3: {
                            text.setBackgroundResource(R.drawable.left_bottom_border_yellow);
                            break;
                        }
                        case 4: {
                            text.setBackgroundResource(R.drawable.top_borders_yellow);
                            break;
                        }
                        case 5: {
                            text.setBackgroundResource(R.drawable.right_border_yellow);
                            break;
                        }
                        case 6: {
                            text.setBackgroundResource(R.drawable.right_top_border_yellow);
                            break;
                        }
                        case 7: {
                            text.setBackgroundResource(R.drawable.right_bottom_border_yellow);
                            break;
                        }
                        case 8: {
                            text.setBackgroundResource(R.drawable.bottom_border_yellow);
                            break;
                        }

                    }
                }
            });
            switch (borders[viewPosition]) {
                case 0: {
                    break;
                }
                case 1: {
                    text.setBackgroundResource(R.drawable.left_border_yellow);
                    break;
                }
                case 2: {
                    text.setBackgroundResource(R.drawable.left_top_border_yellow);
                    break;
                }
                case 3: {
                    text.setBackgroundResource(R.drawable.left_bottom_border_yellow);
                    break;
                }
                case 4: {
                    text.setBackgroundResource(R.drawable.top_borders_yellow);
                    break;
                }
                case 5: {
                    text.setBackgroundResource(R.drawable.right_border_yellow);
                    break;
                }
                case 6: {
                    text.setBackgroundResource(R.drawable.right_top_border_yellow);
                    break;
                }
                case 7: {
                    text.setBackgroundResource(R.drawable.right_bottom_border_yellow);
                    break;
                }
                case 8: {
                    text.setBackgroundResource(R.drawable.bottom_border_yellow);
                    break;
                }
                default: {
                    break;
                }
            }
        } else {

            text.setEnabled(false);
            text.setText(tempNumbersTable[viewPosition]);
            text.setTextColor(Color.BLACK);
            text.setBackgroundColor(Color.WHITE);
            switch (borders[viewPosition]) {
                case 0: {
                    break;
                }
                case 1: {
                    text.setBackgroundResource(R.drawable.left_border);
                    break;
                }
                case 2: {
                    text.setBackgroundResource(R.drawable.left_top_border);
                    break;
                }
                case 3: {
                    text.setBackgroundResource(R.drawable.left_bottom_border);
                    break;
                }
                case 4: {
                    text.setBackgroundResource(R.drawable.top_border);
                    break;
                }
                case 5: {
                    text.setBackgroundResource(R.drawable.right_border);
                    break;
                }
                case 6: {
                    text.setBackgroundResource(R.drawable.right_top_border);
                    break;
                }
                case 7: {
                    text.setBackgroundResource(R.drawable.right_bottom_border);
                    break;
                }
                case 8: {
                    text.setBackgroundResource(R.drawable.bottom_border);
                    break;
                }

            }


        }

        text.setGravity(Gravity.CENTER);
        text.setPadding(0, 0, 0, 0);

        int newsize = (parent.getWidth() - 40) / rowLength; // computing new size

        text.setLayoutParams(new GridView.LayoutParams(newsize, newsize));


        return text;
    }
}

