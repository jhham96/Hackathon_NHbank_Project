package com.example.Hackathon_NHbank_Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AutoScrollAdapter extends PagerAdapter {

    Context context;
    ArrayList<String> data;

    public AutoScrollAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {          // Position 값을 받아 주어진 위치에 페이지를 생성한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.auto_viewpager, null);
        ImageView image_container = (ImageView) v.findViewById(R.id.image_container);
        Glide.with(context).load(data.get(position)).into(image_container);
        container.addView(v);
        return v;
    }
    // 여기서 리턴되는 고유한 Object Key를 가지고 페이지의 해당위치를 알아내고 식별하는데 사용한다.

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {     // Position 값을 받아 주어진 위치에 있는 페이지를 삭제한다.
        container.removeView((View)object);
    }
    // 페이지의 효율성을 유지하기 위해 현재 페이지를 기준으로 -1, +1 위치에 있는 페이지 외에는 destroyItem함수를 통해 삭제된다
    // X O * O X X ==>>  X X O * O X X 이런식으로 페이지가 삭제되고 추가되게 된다.

    @Override
    public int getCount() {     // 사용 가능한 뷰의 개수를 return 시킵니다.
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {       // 페이지 뷰가 생성된 페이지의 Object key와 같은지 확인
        return view == object;                                                           // 즉, 페이지의 뷰가 생성된 뷰인지 확인한다.
    }
}
