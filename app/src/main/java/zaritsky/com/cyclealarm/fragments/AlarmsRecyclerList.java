package zaritsky.com.cyclealarm.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zaritsky.com.cyclealarm.R;
import zaritsky.com.cyclealarm.interfaces.AbleToChangeFragment;
import zaritsky.com.cyclealarm.models.Alarm;
import zaritsky.com.cyclealarm.models.AlarmList;

/**
 * RecyclerView for all alarms of user. The source of data - is the List<Alarm> that is loaded from
 * the model-class  singleton AlarmList
 */
public class AlarmsRecyclerList extends Fragment {
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;
    private List<Alarm> alarmList;
    private AbleToChangeFragment callBackAvtivity;

    /**
     * Interface callBackAvtivity for call the method for change the fragment for the selected position
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackAvtivity = (AbleToChangeFragment) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarms_list_recycler, container, false);
        recyclerView = view.findViewById(R.id.alarms_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = view.findViewById(R.id.floating_button_add_alarm);
        /**
         * create new Alarm with week cycle
         * */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmAdd newAlarm = new AlarmAdd();
                callBackAvtivity.changeFragments(R.id.content_main, newAlarm);
            }
        });
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlarmList alarms = AlarmList.getInstance(getContext());
        alarmList = alarms.getAlarmList();
        adapter = new AlarmAdapter(alarmList, getContext());
    }
    /**adapter for the recyclerView*/
    private class AlarmAdapter extends RecyclerView.Adapter<AlarmViewHolder> {
        List<Alarm> alarmList;
        Context context;

        public AlarmAdapter(List<Alarm> alarmList, Context context) {
            this.alarmList = alarmList;
            this.context = context;
        }

        @Override
        public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_recycler_element, parent, false);
            return new AlarmViewHolder(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(final AlarmViewHolder holder, final int position) {
            final Alarm alarm = alarmList.get(position);
            holder.timeOfAlarm.setText(alarm.getFormatedTime());
            //TODO отображение цикла сработки будильника
            holder.daysOfActive.setText("Пн Вт Ср Чт Пт Сб Вс"/*alarm.getDatesOfActiveCycle()*/);
            final Switch activeAlarm = holder.onOffAlarmSwitch;
            activeAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (activeAlarm.isChecked()) {
                        alarm.setActive(true);
                    } else alarm.setActive(false);
                }
            });
            //TODO увеличение элемента для оотображения мелодии сигнала и напоминание (и мб элементы)
            holder.expandElementDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(), "Меню раздвинуто", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            holder.alarmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * call fragment for the selected position
                     * */
                    AlarmAdd alarm = AlarmAdd.newInstance(position);
                    callBackAvtivity.onSelectedFragment(alarm, holder.getAdapterPosition());
                }
            });

        }

        @Override
        public int getItemCount() {
            if (alarmList != null) {
                return alarmList.size();
            } else return 0;
        }
    }
    /**viewHolder for the recyclerView*/
    static class AlarmViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout alarmView;
        TextView timeOfAlarm;
        Switch onOffAlarmSwitch;
        TextView daysOfActive;
        ImageView expandElementDown;

        AlarmViewHolder(View itemView) {
            super(itemView);
            alarmView = itemView.findViewById(R.id.alarms_recycler_view);
            timeOfAlarm = itemView.findViewById(R.id.time_of_alarm_text_view);
            onOffAlarmSwitch = itemView.findViewById(R.id.on_or_off_switch);
            daysOfActive = itemView.findViewById(R.id.day_of_active_text_view);
            expandElementDown = itemView.findViewById(R.id.to_expand_element_down);
        }

    }
}
