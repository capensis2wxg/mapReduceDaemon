package com.wxg.mapreduce.__06全排序;/*
    @author wxg
    @date 2021/5/20-17:51
    */


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    private final FlowBean outK = new FlowBean();
    private final Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取一行
        String line = value.toString();

        //切割
        String[] split = line.split("\t");

        //封装
        outV.set(split[0]);
        outK.setUpFlow(Long.parseLong(split[1]));
        outK.setDownFlow(Long.parseLong(split[2]));
        outK.setSumFlow();

        //写出
        context.write(outK, outV);
    }
}
