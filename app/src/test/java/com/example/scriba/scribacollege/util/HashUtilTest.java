package com.example.scriba.scribacollege.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Ian Cunningham
 */
public class HashUtilTest {

    private String expected = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
    private String input = "password";
    private String output;

    @Test
    public void sha256() throws Exception {
        output = HashUtil.sha256(input);
        assertEquals(expected, output);
    }
}