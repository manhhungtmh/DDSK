package com.example.ddsk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddsk.R;
import com.example.ddsk.Event;

import java.util.List;

//import static com.example.btl.activity.AttendanceBarcodeActivity.dataListLop;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Event> lstEvent;
    public EventAdapter(Context c, List<Event> listClass)
    {
        context =c;
        this.lstEvent = listClass;
    }

    @Override
    public int getCount() {
        return lstEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
        {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.row_item_event,null);
        }
        TextView tensukien = convertView.findViewById(R.id.txtTenSuKien);
        TextView ngaybatdau = convertView.findViewById(R.id.txtThoiGianBatDau);
        TextView ngayketthuc = convertView.findViewById(R.id.txtThoiGianKetThuc);
        TextView diadiem = convertView.findViewById(R.id.txtDiaDiemToChuc);
        TextView macode = convertView.findViewById(R.id.txtCode);

        if(lstEvent.size()!=0) {
//            tensukien.setText(lstEvent);
            tensukien.setText(lstEvent.get(position).getTenSuKien());
            ngaybatdau.setText(lstEvent.get(position).getThoiGianBatDau().toString());
            ngayketthuc.setText(lstEvent.get(position).getThoiGianKetThuc().toString());
            diadiem.setText(lstEvent.get(position).getDiaChi());
            macode.setText(lstEvent.get(position).getMaCode());
        } else {
            Toast.makeText(context, "Hiện chưa có sự kiện", Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }
}
