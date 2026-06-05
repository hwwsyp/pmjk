/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.xss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HTMLFilter {
    private static final int REGEX_FLAGS_SI = 34;
    private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", 32);
    private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", 34);
    private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", 32);
    private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", 34);
    private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", 34);
    private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", 34);
    private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", 34);
    private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", 34);
    private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
    private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
    private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
    private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
    private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", 32);
    private static final Pattern P_END_ARROW = Pattern.compile("^>");
    private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
    private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
    private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
    private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
    private static final Pattern P_AMP = Pattern.compile("&");
    private static final Pattern P_QUOTE = Pattern.compile("<");
    private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
    private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
    private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");
    private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap<String, Pattern>();
    private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap<String, Pattern>();
    private final Map<String, List<String>> vAllowed;
    private final Map<String, Integer> vTagCounts = new HashMap<String, Integer>();
    private final String[] vSelfClosingTags;
    private final String[] vNeedClosingTags;
    private final String[] vDisallowed;
    private final String[] vProtocolAtts;
    private final String[] vAllowedProtocols;
    private final String[] vRemoveBlanks;
    private final String[] vAllowedEntities;
    private final boolean stripComment;
    private final boolean encodeQuotes;
    private boolean vDebug = false;
    private final boolean alwaysMakeTags;

    public HTMLFilter() {
        this.vAllowed = new HashMap<String, List<String>>();
        ArrayList<String> a_atts = new ArrayList<String>();
        a_atts.add("href");
        a_atts.add("target");
        this.vAllowed.put("a", a_atts);
        ArrayList<String> img_atts = new ArrayList<String>();
        img_atts.add("src");
        img_atts.add("width");
        img_atts.add("height");
        img_atts.add("alt");
        this.vAllowed.put("img", img_atts);
        ArrayList no_atts = new ArrayList();
        this.vAllowed.put("b", no_atts);
        this.vAllowed.put("strong", no_atts);
        this.vAllowed.put("i", no_atts);
        this.vAllowed.put("em", no_atts);
        this.vSelfClosingTags = new String[]{"img"};
        this.vNeedClosingTags = new String[]{"a", "b", "strong", "i", "em"};
        this.vDisallowed = new String[0];
        this.vAllowedProtocols = new String[]{"http", "mailto", "https"};
        this.vProtocolAtts = new String[]{"src", "href"};
        this.vRemoveBlanks = new String[]{"a", "b", "strong", "i", "em"};
        this.vAllowedEntities = new String[]{"amp", "gt", "lt", "quot"};
        this.stripComment = true;
        this.encodeQuotes = true;
        this.alwaysMakeTags = true;
    }

    public HTMLFilter(boolean debug) {
        this();
        this.vDebug = debug;
    }

    public HTMLFilter(Map<String, Object> conf) {
        assert (conf.containsKey("vAllowed")) : "configuration requires vAllowed";
        assert (conf.containsKey("vSelfClosingTags")) : "configuration requires vSelfClosingTags";
        assert (conf.containsKey("vNeedClosingTags")) : "configuration requires vNeedClosingTags";
        assert (conf.containsKey("vDisallowed")) : "configuration requires vDisallowed";
        assert (conf.containsKey("vAllowedProtocols")) : "configuration requires vAllowedProtocols";
        assert (conf.containsKey("vProtocolAtts")) : "configuration requires vProtocolAtts";
        assert (conf.containsKey("vRemoveBlanks")) : "configuration requires vRemoveBlanks";
        assert (conf.containsKey("vAllowedEntities")) : "configuration requires vAllowedEntities";
        this.vAllowed = Collections.unmodifiableMap((HashMap)conf.get("vAllowed"));
        this.vSelfClosingTags = (String[])conf.get("vSelfClosingTags");
        this.vNeedClosingTags = (String[])conf.get("vNeedClosingTags");
        this.vDisallowed = (String[])conf.get("vDisallowed");
        this.vAllowedProtocols = (String[])conf.get("vAllowedProtocols");
        this.vProtocolAtts = (String[])conf.get("vProtocolAtts");
        this.vRemoveBlanks = (String[])conf.get("vRemoveBlanks");
        this.vAllowedEntities = (String[])conf.get("vAllowedEntities");
        this.stripComment = conf.containsKey("stripComment") ? (Boolean)conf.get("stripComment") : true;
        this.encodeQuotes = conf.containsKey("encodeQuotes") ? (Boolean)conf.get("encodeQuotes") : true;
        this.alwaysMakeTags = conf.containsKey("alwaysMakeTags") ? (Boolean)conf.get("alwaysMakeTags") : true;
    }

    private void reset() {
        this.vTagCounts.clear();
    }

    private void debug(String msg) {
        if (this.vDebug) {
            Logger.getAnonymousLogger().info(msg);
        }
    }

    public static String chr(int decimal) {
        return String.valueOf((char)decimal);
    }

    public static String htmlSpecialChars(String s) {
        String result = s;
        result = HTMLFilter.regexReplace(P_AMP, "&amp;", result);
        result = HTMLFilter.regexReplace(P_QUOTE, "&quot;", result);
        result = HTMLFilter.regexReplace(P_LEFT_ARROW, "&lt;", result);
        result = HTMLFilter.regexReplace(P_RIGHT_ARROW, "&gt;", result);
        return result;
    }

    public String filter(String input) {
        this.reset();
        String s = input;
        this.debug("************************************************");
        this.debug("              INPUT: " + input);
        s = this.escapeComments(s);
        this.debug("     escapeComments: " + s);
        s = this.balanceHTML(s);
        this.debug("        balanceHTML: " + s);
        s = this.checkTags(s);
        this.debug("          checkTags: " + s);
        s = this.processRemoveBlanks(s);
        this.debug("processRemoveBlanks: " + s);
        s = this.validateEntities(s);
        this.debug("    validateEntites: " + s);
        this.debug("************************************************\n\n");
        return s;
    }

    public boolean isAlwaysMakeTags() {
        return this.alwaysMakeTags;
    }

    public boolean isStripComments() {
        return this.stripComment;
    }

    private String escapeComments(String s) {
        Matcher m = P_COMMENTS.matcher(s);
        StringBuffer buf = new StringBuffer();
        if (m.find()) {
            String match = m.group(1);
            m.appendReplacement(buf, Matcher.quoteReplacement("<!--" + HTMLFilter.htmlSpecialChars(match) + "-->"));
        }
        m.appendTail(buf);
        return buf.toString();
    }

    private String balanceHTML(String s) {
        if (this.alwaysMakeTags) {
            s = HTMLFilter.regexReplace(P_END_ARROW, "", s);
            s = HTMLFilter.regexReplace(P_BODY_TO_END, "<$1>", s);
            s = HTMLFilter.regexReplace(P_XML_CONTENT, "$1<$2", s);
        } else {
            s = HTMLFilter.regexReplace(P_STRAY_LEFT_ARROW, "&lt;$1", s);
            s = HTMLFilter.regexReplace(P_STRAY_RIGHT_ARROW, "$1$2&gt;<", s);
            s = HTMLFilter.regexReplace(P_BOTH_ARROWS, "", s);
        }
        return s;
    }

    private String checkTags(String s) {
        Matcher m = P_TAGS.matcher(s);
        StringBuffer buf = new StringBuffer();
        while (m.find()) {
            String replaceStr = m.group(1);
            replaceStr = this.processTag(replaceStr);
            m.appendReplacement(buf, Matcher.quoteReplacement(replaceStr));
        }
        m.appendTail(buf);
        s = buf.toString();
        for (String key : this.vTagCounts.keySet()) {
            int ii = 0;
            while (ii < this.vTagCounts.get(key)) {
                s = String.valueOf(s) + "</" + key + ">";
                ++ii;
            }
        }
        return s;
    }

    private String processRemoveBlanks(String s) {
        String result = s;
        String[] stringArray = this.vRemoveBlanks;
        int n = this.vRemoveBlanks.length;
        int n2 = 0;
        while (n2 < n) {
            String tag = stringArray[n2];
            if (!P_REMOVE_PAIR_BLANKS.containsKey(tag)) {
                P_REMOVE_PAIR_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?></" + tag + ">"));
            }
            result = HTMLFilter.regexReplace((Pattern)P_REMOVE_PAIR_BLANKS.get(tag), "", result);
            if (!P_REMOVE_SELF_BLANKS.containsKey(tag)) {
                P_REMOVE_SELF_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?/>"));
            }
            result = HTMLFilter.regexReplace((Pattern)P_REMOVE_SELF_BLANKS.get(tag), "", result);
            ++n2;
        }
        return result;
    }

    private static String regexReplace(Pattern regex_pattern, String replacement, String s) {
        Matcher m = regex_pattern.matcher(s);
        return m.replaceAll(replacement);
    }

    private String processTag(String s) {
        String name;
        Matcher m = P_END_TAG.matcher(s);
        if (m.find() && this.allowed(name = m.group(1).toLowerCase()) && !HTMLFilter.inArray(name, this.vSelfClosingTags) && this.vTagCounts.containsKey(name)) {
            this.vTagCounts.put(name, this.vTagCounts.get(name) - 1);
            return "</" + name + ">";
        }
        m = P_START_TAG.matcher(s);
        if (m.find()) {
            name = m.group(1).toLowerCase();
            String body = m.group(2);
            String ending = m.group(3);
            if (this.allowed(name)) {
                String params = "";
                Matcher m2 = P_QUOTED_ATTRIBUTES.matcher(body);
                Matcher m3 = P_UNQUOTED_ATTRIBUTES.matcher(body);
                ArrayList<String> paramNames = new ArrayList<String>();
                ArrayList<String> paramValues = new ArrayList<String>();
                while (m2.find()) {
                    paramNames.add(m2.group(1));
                    paramValues.add(m2.group(3));
                }
                while (m3.find()) {
                    paramNames.add(m3.group(1));
                    paramValues.add(m3.group(3));
                }
                int ii = 0;
                while (ii < paramNames.size()) {
                    String paramName = ((String)paramNames.get(ii)).toLowerCase();
                    String paramValue = (String)paramValues.get(ii);
                    if (this.allowedAttribute(name, paramName)) {
                        if (HTMLFilter.inArray(paramName, this.vProtocolAtts)) {
                            paramValue = this.processParamProtocol(paramValue);
                        }
                        params = String.valueOf(params) + " " + paramName + "=\"" + paramValue + "\"";
                    }
                    ++ii;
                }
                if (HTMLFilter.inArray(name, this.vSelfClosingTags)) {
                    ending = " /";
                }
                if (HTMLFilter.inArray(name, this.vNeedClosingTags)) {
                    ending = "";
                }
                if (ending == null || ending.length() < 1) {
                    if (this.vTagCounts.containsKey(name)) {
                        this.vTagCounts.put(name, this.vTagCounts.get(name) + 1);
                    } else {
                        this.vTagCounts.put(name, 1);
                    }
                } else {
                    ending = " /";
                }
                return "<" + name + params + ending + ">";
            }
            return "";
        }
        m = P_COMMENT.matcher(s);
        if (!this.stripComment && m.find()) {
            return "<" + m.group() + ">";
        }
        return "";
    }

    private String processParamProtocol(String s) {
        String protocol;
        Matcher m = P_PROTOCOL.matcher(s = this.decodeEntities(s));
        if (m.find() && !HTMLFilter.inArray(protocol = m.group(1), this.vAllowedProtocols) && (s = "#" + s.substring(protocol.length() + 1, s.length())).startsWith("#//")) {
            s = "#" + s.substring(3, s.length());
        }
        return s;
    }

    private String decodeEntities(String s) {
        int decimal;
        String match;
        StringBuffer buf = new StringBuffer();
        Matcher m = P_ENTITY.matcher(s);
        while (m.find()) {
            match = m.group(1);
            decimal = Integer.decode(match);
            m.appendReplacement(buf, Matcher.quoteReplacement(HTMLFilter.chr(decimal)));
        }
        m.appendTail(buf);
        s = buf.toString();
        buf = new StringBuffer();
        m = P_ENTITY_UNICODE.matcher(s);
        while (m.find()) {
            match = m.group(1);
            decimal = Integer.valueOf(match, 16);
            m.appendReplacement(buf, Matcher.quoteReplacement(HTMLFilter.chr(decimal)));
        }
        m.appendTail(buf);
        s = buf.toString();
        buf = new StringBuffer();
        m = P_ENCODE.matcher(s);
        while (m.find()) {
            match = m.group(1);
            decimal = Integer.valueOf(match, 16);
            m.appendReplacement(buf, Matcher.quoteReplacement(HTMLFilter.chr(decimal)));
        }
        m.appendTail(buf);
        s = buf.toString();
        s = this.validateEntities(s);
        return s;
    }

    private String validateEntities(String s) {
        StringBuffer buf = new StringBuffer();
        Matcher m = P_VALID_ENTITIES.matcher(s);
        while (m.find()) {
            String one = m.group(1);
            String two = m.group(2);
            m.appendReplacement(buf, Matcher.quoteReplacement(this.checkEntity(one, two)));
        }
        m.appendTail(buf);
        return this.encodeQuotes(buf.toString());
    }

    private String encodeQuotes(String s) {
        if (this.encodeQuotes) {
            StringBuffer buf = new StringBuffer();
            Matcher m = P_VALID_QUOTES.matcher(s);
            while (m.find()) {
                String one = m.group(1);
                String two = m.group(2);
                String three = m.group(3);
                m.appendReplacement(buf, Matcher.quoteReplacement(String.valueOf(one) + HTMLFilter.regexReplace(P_QUOTE, "&quot;", two) + three));
            }
            m.appendTail(buf);
            return buf.toString();
        }
        return s;
    }

    private String checkEntity(String preamble, String term) {
        return ";".equals(term) && this.isValidEntity(preamble) ? String.valueOf('&') + preamble : "&amp;" + preamble;
    }

    private boolean isValidEntity(String entity) {
        return HTMLFilter.inArray(entity, this.vAllowedEntities);
    }

    private static boolean inArray(String s, String[] array) {
        String[] stringArray = array;
        int n = array.length;
        int n2 = 0;
        while (n2 < n) {
            String item = stringArray[n2];
            if (item != null && item.equals(s)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    private boolean allowed(String name) {
        return (this.vAllowed.isEmpty() || this.vAllowed.containsKey(name)) && !HTMLFilter.inArray(name, this.vDisallowed);
    }

    private boolean allowedAttribute(String name, String paramName) {
        return this.allowed(name) && (this.vAllowed.isEmpty() || this.vAllowed.get(name).contains(paramName));
    }
}

