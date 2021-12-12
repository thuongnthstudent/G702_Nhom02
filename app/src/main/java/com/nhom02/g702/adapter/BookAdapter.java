package com.nhom02.g702.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom02.g702.R;
import com.nhom02.g702.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getItem_layout() {
        return item_layout;
    }

    public void setItem_layout(int item_layout) {
        this.item_layout = item_layout;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    private int item_layout;
    private List<Book> book;

    public BookAdapter(Context context, int item_layout, List<Book> book) {
        this.context = context;
        this.item_layout = item_layout;
        this.book = book;
    }


    @Override
    public int getCount() {
        return book.size();
    }

    @Override
    public Object getItem(int position) {
        return book.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout,null);
            holder.imvAnh=view.findViewById(R.id.imvBook);
            holder.txtTen=view.findViewById(R.id.txtTenSach);
            holder.txtGia=view.findViewById(R.id.txtGia);
            holder.txtNhaXB=view.findViewById(R.id.txtNhaXB);
            holder.txtID=view.findViewById(R.id.txtID);
            holder.txtSoLan=view.findViewById(R.id.txtSoLan);
            view.setTag(holder);
        }else {
            holder=(ViewHolder) view.getTag();
        }
        Book s = book.get(i);
        holder.txtID.setText(s.getW_id());
        holder.txtTen.setText(s.getW_ten());
        holder.txtGia.setText(String.valueOf(s.getW_gia()));
        holder.txtSoLan.setText(s.getW_solantb());
        holder.txtNhaXB.setText(s.getW_nhaxb());

        //convert byte array ->bitmap
        byte[] photo = s.getW_anh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0,photo.length);
        holder.imvAnh.setImageBitmap(bitmap);

        return view;
    }

    private class ViewHolder{
        TextView txtID, txtTen, txtNhaXB, txtSoLan, txtGia;
        ImageView imvAnh;
    }
}
