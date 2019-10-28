package cn.zhangchg;

import cn.zhangchg.model.Block;
import cn.zhangchg.model.BlockOld;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {
    public static int difficulty = 6;
    public static ArrayList<BlockOld> blocks = new ArrayList<BlockOld>();
    public static void main(String[] args) {
        blocks.add(new BlockOld("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blocks.get(0).mineBlock(difficulty);
        blocks.add(new BlockOld("Yo im the second block", blocks.get(blocks.size()-1).hash));
        System.out.println("Trying to Mine block 1... ");
        blocks.get(1).mineBlock(difficulty);
        blocks.add(new BlockOld("Hey im the third block", blocks.get(blocks.size()-1).hash));
        System.out.println("Trying to Mine block 1... ");
        blocks.get(2).mineBlock(difficulty);
        System.out.println("\nBlockchain is Valid: " + Block.isChainValid(blocks, difficulty));
        System.err.println(JSONArray.toJSONString(blocks));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocks);
        System.out.println(blockchainJson);
        System.out.println("\nBlockchain is Valid: " + Block.isChainValid(blocks, difficulty));
    }
}
