package com.mustafa.turkeynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mustafa.turkeynewsapp.newsApiJSON.Article;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NewsHolder> {

    List<Article> articles;
    Context context;
    int positionNews;

    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    public NewsUrlGo newsUrlGo;

    public interface NewsUrlGo{
       void newsClick(String url);
    }
    public void setNewsUrlGo(NewsUrlGo newsUrlGo){
        this.newsUrlGo = newsUrlGo;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.desc.setText(article.getDescription());
        holder.newsname.setText(article.getSource().getName());
        holder.times.setText(Utils.DateFormat(article.getPublishedAt()));
        holder.add_time.setText(Utils.DateToTimeFormat(article.getPublishedAt()));
        Glide.with(context).load(article.getUrlToImage()).into(holder.newsImage);
       holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        TextView title,desc,newsname,times,add_time;
        ImageView newsImage;
        FrameLayout frameLayout;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            newsname = itemView.findViewById(R.id.newsName);
            newsImage = itemView.findViewById(R.id.news_image);
            times = itemView.findViewById(R.id.times);
            add_time = itemView.findViewById(R.id.add_time);
            frameLayout = itemView.findViewById(R.id.frame);



        }
     public void bind(Article article){

         frameLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (newsUrlGo != null){
                     newsUrlGo.newsClick(article.getUrl());
                 }
             }
         });
        }



    }

}
