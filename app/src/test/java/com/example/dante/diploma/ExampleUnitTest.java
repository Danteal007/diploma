package com.example.dante.diploma;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void CheckModifyScript_0() {
        String expect = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\\\"Hello\\\");" +
                "    }" +
                "    }";
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\"Hello\");" +
                "    }" +
                "    }";
        assertEquals(expect, CommonUtils.ModifyScript(script));
    }

    @Test
    public void CheckModifyScript_1() {
        String expect = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\\\"Some text\\\");" +
                "    Console.Write(\\\"very formatted output\\\");" +
                "    }" +
                "    }";
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\"Some text\");" +
                "    Console.Write(\"very formatted output\");" +
                "    }" +
                "    }";
        assertEquals(expect, CommonUtils.ModifyScript(script));
    }

    @Test
    public void CheckModifyScript_2() {
        String expect = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    String output = \\\"Hello world!\\\";" +
                "    Console.Write(\\\"Hello!\\\" + output);" +
                "    }" +
                "    }";
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    String output = \"Hello world!\";" +
                "    Console.Write(\"Hello!\" + output);" +
                "    }" +
                "    }";
        assertEquals(expect, CommonUtils.ModifyScript(script));
    }

    @Test
    public void CheckApi_0(){
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\"Hello\");" +
                "    }" +
                "    }";
        String output = CommonUtils.CallAPI(script);
        assertTrue(output.contains("Hello"));
    }

    @Test
    public void CheckApi_1(){
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    {" +
                "    Console.Write(\"Some text, \");" +
                "    Console.Write(\"very formatted output\");" +
                "    }" +
                "    }";
        String output = CommonUtils.CallAPI(script);
        assertTrue(output.contains("Some text, very formatted output"));
    }

    @Test
    public void CheckApi_2(){
        String script = "using System;" +
                "    class Program  {" +
                "static void Main(string[] args)    " +
                "    }" +
                "    }";
        String output = CommonUtils.CallAPI(script);
        assertTrue(output.contains("Compilation failed"));
    }
}