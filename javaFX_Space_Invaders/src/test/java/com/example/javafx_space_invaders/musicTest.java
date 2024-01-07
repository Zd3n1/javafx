//package com.example.javafx_space_invaders;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runners.Parameterized;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.runner.RunWith;
//import static org.junit.Assert.assertEquals;
//
//import java.util.Arrays;
//import java.util.Collection;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import static org.junit.Assert.assertEquals;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//@RunWith(Parameterized.class)
//public class MusicTest {
//
//    private final String version;
//    private final String expectedMusicFile;
//
//    public MusicTest(String version, String expectedMusicFile) {
//        this.version = version;
//        this.expectedMusicFile = expectedMusicFile;
//    }
//
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList(new Object[][]{
//                {"8bit/b", "b.mp3"},
//                {"sw/SW", "SW.mp3"},
//                {"st/ST", "ST.mp3"},
//                {"unknownVersion", "c.mp3"}
//        });
//    }
//
//    @Test
//    public void testMusic() {
//
////        assertEquals(expectedMusicFile, result);
//    }
//}
//}