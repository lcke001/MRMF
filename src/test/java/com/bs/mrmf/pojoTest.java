package com.bs.mrmf;

import com.bs.mrmf.pojo.administrator;
import org.junit.jupiter.api.Test;

public class pojoTest {
    @Test
    public void adminTest(){
        administrator a = new administrator(1,"1111","1111","ka","在用");
        a.setAccount("aaaa");
        System.out.println(a);
    }
}
