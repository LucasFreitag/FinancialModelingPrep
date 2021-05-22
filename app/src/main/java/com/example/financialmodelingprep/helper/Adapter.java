package com.example.financialmodelingprep.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialmodelingprep.R;
import com.example.financialmodelingprep.model.Quote;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Quote> listQuotes;

    public Adapter(List<Quote> listQuotes) {
        this.listQuotes = listQuotes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quote quote = listQuotes.get(position);

        holder.simbolo.setText(quote.getSimbolo());
        holder.nome.setText(quote.getNome());
        holder.preco.setText(String.valueOf(quote.getPreco()));
    }

    @Override
    public int getItemCount() {
        return listQuotes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView simbolo, nome, preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.tvNome);
            simbolo = itemView.findViewById(R.id.tvSimbolo);
            preco = itemView.findViewById(R.id.tvPreco);
        }
    }
}
