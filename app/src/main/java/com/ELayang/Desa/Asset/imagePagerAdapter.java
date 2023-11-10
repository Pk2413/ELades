package com.ELayang.Desa.Asset;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ELayang.Desa.R;

public class imagePagerAdapter extends PagerAdapter {

    private Context context;
    private int[] images = {R.drawable.foto1, R.drawable.noimage};
    private final int MAX_VALUE = 1000;

    public imagePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.image_pager_adapter, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);

        // Menggunakan modulo untuk mendapatkan indeks gambar yang valid
        int imagePosition = position % images.length;

        // Set gambar ke ImageView
        imageView.setImageResource(images[imagePosition]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // Menggunakan nilai maksimum untuk membuat loop tak terbatas
        return MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
