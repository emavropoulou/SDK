package another.sudoku.helpers;

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

import another.sudoku.R;

public class NumbersAdapter extends BaseAdapter {
    private String[] itemsName;
    private Context context;

    private int[] borders;
    private List<Integer> editablePositions;
    private int Squared;
    private long pos;

    public NumbersAdapter(Context context, int[][] ItemsName) {
        pos = -1;
        editablePositions = new ArrayList<>();
        int squaredRoot = (int) Math.sqrt(ItemsName.length);
        Squared = ItemsName.length;
        itemsName = new String[ItemsName.length * ItemsName.length];
        borders = new int[ItemsName.length * ItemsName.length];
        int count = 0;
        for (int i = 0; i < ItemsName.length; i++) {
            for (int j = 0; j < ItemsName.length; j++) {
                if (i == 0 && j % squaredRoot == 0) {
                    borders[count] = 2;
                } else if (i == 0 && j == ItemsName.length - 1) {
                    borders[count] = 6;
                } else if (i == 0 && j != ItemsName.length - 1 && j % squaredRoot != 0) {
                    borders[count] = 4;
                } else if (i != 0 && i % squaredRoot == squaredRoot - 1 && j % squaredRoot == 0) {
                    borders[count] = 3;
                } else if (i != 0 && i % squaredRoot == squaredRoot - 1 && j != ItemsName.length - 1 && j % squaredRoot != 0) {
                    borders[count] = 8;
                } else if (i != 0 && i % squaredRoot == squaredRoot - 1 && j == ItemsName.length - 1 && j % squaredRoot != 0) {
                    borders[count] = 7;
                } else if (i != 0 && (i % squaredRoot != squaredRoot - 1) && j % squaredRoot == 0) {
                    borders[count] = 1;
                } else if (i != 0 && (i % squaredRoot != squaredRoot - 1) && j == ItemsName.length - 1) {
                    borders[count] = 5;
                } else {
                    borders[count] = 0;
                }

                if (ItemsName[i][j] != 0) {
                    this.itemsName[count] = String.valueOf(ItemsName[i][j]);
                } else {
                    this.itemsName[count] = " ";
                    this.editablePositions.add(count);
                }
                count = count + 1;
            }

        }
        this.context = context;
    }

    @Override
    public int getCount() {

        return itemsName.length;
    }

    @Override
    public Object getItem(int position) {
        return itemsName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public long getPosition() {
        return pos;
    }

    public void setNum(int num, int position, int size) {


        switch (size) {
            case 3:
                if (num != 0) {
                    if (position >= 0) {
                        StringBuilder s = new StringBuilder();
                        s.append(itemsName[position]);
                        s.append(String.valueOf(num));
                        if (s.toString().length() > 2) {
                            s.deleteCharAt(1);
                        }
                        itemsName[position] = s.toString();
                        this.notifyDataSetChanged();
                    }
                }
                break;
            case 4:
                if (position >= 0) {
                    StringBuilder s = new StringBuilder();
                    s.append(itemsName[position]);
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
                    itemsName[position] = s.toString();
                    this.notifyDataSetChanged();
                }
                break;


        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EditText text = new EditText(this.context);


        if ((pos == position)) {
            text.setEnabled(true);
            text.setBackgroundColor(Color.parseColor("#ffffbb33"));
            text.setInputType(InputType.TYPE_NULL);
            text.setOnFocusChangeListener((v, event) -> {

                if (event) {
                    pos = position;
                    text.setBackgroundColor(Color.GREEN);
                } else {
                    pos = -1;
                    text.setBackgroundColor(Color.WHITE);
                    switch (borders[position]) {
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
            switch (borders[position]) {
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
        text.setText(itemsName[position]);
        if (itemsName[position].equals(" ") || editablePositions.contains(position)) {
            text.setEnabled(true);
            text.setBackgroundColor(Color.parseColor("#ffffbb33"));
            text.setInputType(InputType.TYPE_NULL);

            text.setOnFocusChangeListener((v, event) -> {
                if (event) {
                    pos = position;
                    text.setBackgroundColor(Color.GREEN);
                } else {
                    pos = -1;
                    text.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    ;
                    switch (borders[position]) {
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
            switch (borders[position]) {
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
            text.setText(itemsName[position]);
            text.setTextColor(Color.BLACK);
            text.setBackgroundColor(Color.WHITE);
            switch (borders[position]) {
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

        int newsize = (parent.getWidth() - 40) / Squared; // computing new size

        text.setLayoutParams(new GridView.LayoutParams(newsize, newsize));


        return text;
    }
}

