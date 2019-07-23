package jp.com.studentproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resoure;
    private List<Student> listStudent;

    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }


    public CustomAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resoure;
        this.listStudent = objects;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.show_all_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId                         = (TextView)convertView.findViewById(R.id.tv_id);
            viewHolder.tvName                       = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvAge                        = (TextView)convertView.findViewById(R.id.tv_age);
            viewHolder.tvSex                        = (TextView)convertView.findViewById(R.id.tv_sex);
            viewHolder.tvPhone                     = (TextView)convertView.findViewById(R.id.tv_phone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = listStudent.get(position);
        viewHolder.tvId.setText(String.valueOf(student.getId()));
        viewHolder.tvName.setText(student.getName());
        viewHolder.tvAge.setText(student.getAge() + "");
        viewHolder.tvSex.setText(student.getSex() + "");
        viewHolder.tvPhone.setText(student.getPhoneNumber() + "");

        return convertView;
    }



    public class ViewHolder {

        private TextView tvId;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvSex;
        private TextView tvPhone;
    }

}
