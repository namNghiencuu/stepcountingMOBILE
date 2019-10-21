package admin.example.demosql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//ở đây  có thể dùng nhiều cách . tại đây  dùng baseAdapter. làm recylceview cũng đk nhưng phải add thư viện. ok

public class DemoAdapter extends BaseAdapter {
    private  MainActivity context; //ở đây ta có thể đặt contexxt thay vì Main nhưng mà tí nữa phần sửa và xóa mình cần phải gọi activity khác
    private List<DeMo> deMoList;
    private int layout;

    public DemoAdapter(MainActivity context, List<DeMo>deMoList, int layout) {
        this.context = context;
        this.deMoList = deMoList;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return deMoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTen;
        ImageView imgEdit,imgDel;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(layout,null);
            viewHolder.txtTen=view.findViewById(R.id.txt_hoten);
            viewHolder.imgEdit=view.findViewById(R.id.img_edit);
            viewHolder.imgDel=view.findViewById(R.id.img_del);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();

        }
        final DeMo deMo=deMoList.get(position);
        viewHolder.txtTen.setText(deMo.getTen());

        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deMoSua(deMo.getTen(),deMo.getId());
            }
        });
        viewHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deMoXoa(deMo.getTen(),deMo.getId());
            }
        });
        return view;
    }
}
