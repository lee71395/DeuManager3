package com.example.deumanager3;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.deumanager3.model.ScheduleModel;
import com.example.deumanager3.singleton.Schedule;
import com.google.firebase.database.DatabaseReference;
import com.example.deumanager3.ScheduleDialogFragment;
import java.util.List;

public class ScheduleFragment extends ToolBarFragment {
    private static final String DIALOG_SCHEDULE = "DialogSchedule";
//    private static ScheduleFragment sFragment = new ScheduleFragment();
    private static ScheduleModel scheduleModel = new ScheduleModel();
    private Schedule schedule;
    private List<Schedule> mSchedules;
    private boolean scheduleDelete = false;
    private DatabaseReference databaseReference;

    private TextView monday[] = new TextView[8];
    private TextView tuesday[] = new TextView[8];
    private TextView wednesday[] = new TextView[8];
    private TextView thursday[] = new TextView[8];
    private TextView friday[] = new TextView[8];

//
//    @NonNull
//    public static ScheduleFragment newInstance() {
//        return sFragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        monday[0] = view.findViewById(R.id.monday1);
        monday[1] = view.findViewById(R.id.monday2);
        monday[2] = view.findViewById(R.id.monday3);
        monday[3] = view.findViewById(R.id.monday4);
        monday[4] = view.findViewById(R.id.monday5);
        monday[5] = view.findViewById(R.id.monday6);
        monday[6] = view.findViewById(R.id.monday7);
        monday[7] = view.findViewById(R.id.monday8);

        tuesday[0] = view.findViewById(R.id.tuesday1);
        tuesday[1] = view.findViewById(R.id.tuesday2);
        tuesday[2] = view.findViewById(R.id.tuesday3);
        tuesday[3] = view.findViewById(R.id.tuesday4);
        tuesday[4] = view.findViewById(R.id.tuesday5);
        tuesday[5] = view.findViewById(R.id.tuesday6);
        tuesday[6] = view.findViewById(R.id.tuesday7);
        tuesday[7] = view.findViewById(R.id.tuesday8);

        wednesday[0] = view.findViewById(R.id.wednesday1);
        wednesday[1] = view.findViewById(R.id.wednesday2);
        wednesday[2] = view.findViewById(R.id.wednesday3);
        wednesday[3] = view.findViewById(R.id.wednesday4);
        wednesday[4] = view.findViewById(R.id.wednesday5);
        wednesday[5] = view.findViewById(R.id.wednesday6);
        wednesday[6] = view.findViewById(R.id.wednesday7);
        wednesday[7] = view.findViewById(R.id.wednesday8);

        thursday[0] = view.findViewById(R.id.thursday1);
        thursday[1] = view.findViewById(R.id.thursday2);
        thursday[2] = view.findViewById(R.id.thursday3);
        thursday[3] = view.findViewById(R.id.thursday4);
        thursday[4] = view.findViewById(R.id.thursday5);
        thursday[5] = view.findViewById(R.id.thursday6);
        thursday[6] = view.findViewById(R.id.thursday7);
        thursday[7] = view.findViewById(R.id.thursday8);

        friday[0] = view.findViewById(R.id.friday1);
        friday[1] = view.findViewById(R.id.friday2);
        friday[2] = view.findViewById(R.id.friday3);
        friday[3] = view.findViewById(R.id.friday4);
        friday[4] = view.findViewById(R.id.friday5);
        friday[5] = view.findViewById(R.id.friday6);
        friday[6] = view.findViewById(R.id.friday7);
        friday[7] = view.findViewById(R.id.friday8);

        monday[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteClass(view);
            }
        });

        StartCreateView();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                ScheduleDialogFragment dialogFragment = new ScheduleDialogFragment();
                dialogFragment.show(manager, DIALOG_SCHEDULE);
                dialogFragment.setDialogResult(new ScheduleDialogFragment.OnMyDialogResult() {
                    @Override
                    public void finish(Object result) {
                        schedule = (Schedule) result;
                        scheduleModel.writeSchedule(schedule.getClassName(), schedule.getClassRoom(), schedule.getClassDay(), schedule.getClassTime(),schedule.getClassUid());
                        createView(schedule.getClassName(), schedule.getClassRoom(), schedule.getClassDay(), schedule.getClassTime());
                    }
                });
            }
        });

        return view;
    }




    public void StartCreateView(){
        this.mSchedules = scheduleModel.getSchedules();
        for (int i = 0; i < mSchedules.size(); i++) {
            createView(mSchedules.get(i).getClassName(), mSchedules.get(i).getClassRoom(), mSchedules.get(i).getClassDay(), mSchedules.get(i).getClassTime());
        }
    }
    public void ListRemove(String name, String time) {
        mSchedules = scheduleModel.getSchedules();
        for (int i = 0; i < mSchedules.size(); i++) {
            if (mSchedules.get(i).getClassDay().equals(name)) {
                if (mSchedules.get(i).getClassTime().equals(time)) {
                    mSchedules.remove(mSchedules.get(i));
                }
            }
        }
    }

    public void DeleteClass(View v) {
        FragmentManager manager = getFragmentManager();
        ScheduleDeleteDialogFragment deleteDialogFragment = new ScheduleDeleteDialogFragment();
        deleteDialogFragment.show(manager, DIALOG_SCHEDULE);
        final TextView text = (TextView) v;
        deleteDialogFragment.setDeleteDialogResult(new ScheduleDeleteDialogFragment.OnMyDeleteDialogResult() {
            @Override
            public void delete(boolean result) {
                scheduleDelete = result;
                if (scheduleDelete) {
                    switch (text.getId()) {
                        case R.id.monday1:
                            ListRemove("월요일", "1");
                            StartCreateView();
                        case R.id.monday2:
                            ListRemove("월요일", "2");
                        case R.id.monday3:
                            ListRemove("월요일", "3");
                        case R.id.monday4:
                            ListRemove("월요일", "4");
                        case R.id.monday5:
                            ListRemove("월요일", "5");
                        case R.id.monday6:
                            ListRemove("월요일", "6");
                        case R.id.monday7:
                            ListRemove("월요일", "7");
                        case R.id.monday8:
                            ListRemove("월요일", "8");

                    }

                }
            }
        });

    }

    public void createView(String name, String room, String day, String time) {
        String r = " (" + room + ") ";
        String text = name + "\n" + r;
        if (day.equals("월요일")) {
            if (time.equals("1")) {
                monday[0].setText(text);
                monday[0].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("2")) {
                monday[1].setText(text);
                monday[1].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("3")) {
                Toast.makeText(getActivity(), name + " (" + room + ") ", Toast.LENGTH_SHORT).show();
                monday[2].setText(text);
                monday[2].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("4")) {
                monday[3].setText(text);

                monday[3].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("5")) {
                monday[4].setText(text);

                monday[4].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("6")) {
                monday[5].setText(text);

                monday[5].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("7")) {
                monday[6].setText(text);

                monday[6].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("8")) {
                monday[7].setText(text);

                monday[7].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            }

        } else if (day.equals("화요일")) {
            if (time.equals("1")) {
                tuesday[0].setText(text);
                tuesday[0].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("2")) {
                tuesday[1].setText(text);
                tuesday[1].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));

            } else if (time.equals("3")) {
                tuesday[2].setText(text);
                tuesday[2].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("4")) {
                tuesday[3].setText(text);
                tuesday[3].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("5")) {
                tuesday[4].setText(text);
                tuesday[4].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("6")) {
                tuesday[5].setText(text);
                tuesday[5].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("7")) {
                tuesday[6].setText(text);
                tuesday[6].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("8")) {
                tuesday[7].setText(text);
                tuesday[7].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            }
        } else if (day.equals("수요일")) {
            if (time.equals("1")) {
                wednesday[0].setText(text);
                wednesday[0].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("2")) {
                wednesday[1].setText(text);
                wednesday[1].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("3")) {
                wednesday[2].setText(text);
                wednesday[2].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("4")) {
                wednesday[3].setText(text);
                wednesday[3].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("5")) {
                wednesday[4].setText(text);
                wednesday[4].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("6")) {
                wednesday[5].setText(text);
                wednesday[5].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("7")) {
                wednesday[6].setText(text);
                wednesday[6].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("8")) {
                wednesday[7].setText(text);
                wednesday[7].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            }
        } else if (day.equals("목요일")) {
            if (time.equals("1")) {
                thursday[0].setText(text);
                thursday[0].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("2")) {
                thursday[1].setText(text);
                thursday[1].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("3")) {
                thursday[2].setText(text);
                thursday[2].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("4")) {
                thursday[3].setText(text);
                thursday[3].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("5")) {
                thursday[4].setText(text);
                thursday[4].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("6")) {
                thursday[5].setText(text);
                thursday[5].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("7")) {
                thursday[6].setText(text);
                thursday[6].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("8")) {
                thursday[7].setText(text);
                thursday[7].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            }
        } else if (day.equals("금요일")) {
            if (time.equals("1")) {
                friday[0].setText(text);
                friday[0].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("2")) {
                friday[1].setText(text);
                friday[1].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("3")) {
                friday[2].setText(text);
                friday[2].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("4")) {
                friday[3].setText(text);
                friday[3].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("5")) {
                friday[4].setText(text);
                friday[4].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("6")) {
                friday[5].setText(text);
                friday[5].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("7")) {
                friday[6].setText(text);
                friday[6].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            } else if (time.equals("8")) {
                friday[7].setText(text);
                friday[7].setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cell_shape_update));
            }
        }

    }

}