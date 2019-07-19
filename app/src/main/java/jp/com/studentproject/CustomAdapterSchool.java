package jp.com.studentproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapterSchool extends ArrayAdapter<Schoole_Time> {
    private Context context;
    private int resoure;
    private List<Schoole_Time> listSchoolTime;

    public CustomAdapterSchool(Context context, int resource) {
        super(context, resource);
    }


    public CustomAdapterSchool(Context context, int resource, List<Schoole_Time> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resoure;
        this.listSchoolTime = objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.show_school_time, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.btnDay                         = (Button)convertView.findViewById(R.id.btnDay);
            viewHolder.btnTime                       = (Button)convertView.findViewById(R.id.btnTime);
            viewHolder.btnMonday                     = (Button)convertView.findViewById(R.id.btnMonday);
            viewHolder.btnTuesday                    = (Button)convertView.findViewById(R.id.btnTuesday);
            viewHolder.btnWednesday                  = (Button)convertView.findViewById(R.id.btnWednesday);
            viewHolder.btnThursday                   = (Button)convertView.findViewById(R.id.btnThursday);
            viewHolder.btnFriday                     = (Button)convertView.findViewById(R.id.btnFriday);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Schoole_Time schooleTime = listSchoolTime.get(position);
        viewHolder.btnDay.setText(schooleTime.getSession());
        viewHolder.btnTime.setText(schooleTime.getTime());
        viewHolder.btnMonday.setText(schooleTime.getSubjects_monday());
        viewHolder.btnTuesday.setText(schooleTime.getSubjects_tuesday());
        viewHolder.btnWednesday.setText(schooleTime.getSubjects_wednesday());
        viewHolder.btnThursday.setText(schooleTime.getSubjects_thursday());
        viewHolder.btnFriday.setText(schooleTime.getSubjects_friday());

        return convertView;
    }



    public class ViewHolder {
        private Button btnDay;
        private Button btnTime;
        private Button btnMonday;
        private Button btnTuesday;
        private Button btnWednesday;
        private Button btnThursday;
        private Button btnFriday;

    }

}
