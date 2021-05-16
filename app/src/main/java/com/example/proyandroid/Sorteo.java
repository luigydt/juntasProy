package com.example.proyandroid;

import java.util.Random;

public class Sorteo {


    private int nPersonas;
    private int [] sort;


    public int getnPersonas() {
        return nPersonas;
    }
    public int[] getSort() {
        return sort;
    }

    public Sorteo(int nPersonas) {

        this.nPersonas = nPersonas;
        sort = new int[nPersonas];

        for(int i=0;i<sort.length;i++)
        {
            sort[i]=i+1;
        }
        Random r = new Random();
        for(int i = 0;i<sort.length;i++)
        {
            int randomIndexToSwap = r.nextInt(sort.length);
            int temp = sort[randomIndexToSwap];
            sort[randomIndexToSwap] = sort[i];
            sort[i] = temp;
        }
        int p = sort[0];
        for(int i=0;i<sort.length;i++)
        {
            if(sort[i]==1)
            {
                sort[i]=p;
                sort[0]=1;
            }

        }

    }
}
