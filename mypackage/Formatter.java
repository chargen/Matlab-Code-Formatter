package mypackage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Formatter {
    // 分为两种符号类型，第一种符号前后都要留空格，第二种符号只有后面要与字符留空格
    private final String Symbols1 = "+-*/%&:=";
    private final String Symbols2 = ",;";

    public static void main(String[] args) {
        Formatter format = new Formatter();
        format.format();
    }

    public void format() {
        String content = getSysClipboardText();
        StringBuilder ret = new StringBuilder();
        boolean isInString = false;
        for (int i = 0; i < content.length(); i++) {
            char cur = content.charAt(i);
            ret.append(cur);
            if (i == content.length() - 1) continue;
            char next = content.charAt(i + 1);
            if (isSingleQuotes(cur) && isSingleQuotes(next)) {
                // 两个单引号连续出现，第一个单引号为转义，第二个是出现在字符串中的单引号
                continue;
            }
            if (isSingleQuotes(cur)) {
                isInString = !isInString;
                continue;
            }
            if (isInString) {
                continue;
            }
            if (isWord(cur) && isSymbol1(next) || isSymbol1(cur) && isWord(next)
                    || isSymbol2(cur) && isWord(next)
                    || isSymbol2(cur) && isSymbol1(next)
                    || isSymbol1(cur) && isSingleQuotes(next)
                    || isSymbol2(cur) && isSingleQuotes(next)) {
                ret.append(' ');
            }
        }
//        System.out.println(ret.toString());
        setSysClipboardText(ret.toString());
    }

    private boolean isWord(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9';
    }

    private boolean isSymbol1(char c) {
        return Symbols1.indexOf(c) != -1;
    }

    private boolean isSymbol2(char c) {
        return Symbols2.indexOf(c) != -1;
    }

    private boolean isSingleQuotes(char c) {
        return c == '\'';
    }


    /**
     * 读取剪切板内容
     */
    private String getSysClipboardText() {
        Transferable clipTf = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (clipTf != null && clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return (String) clipTf.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 将内容写入剪切板
     */
    private void setSysClipboardText(String str) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(str);
        clip.setContents(tText, null);
    }
}
