class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = new TreeSet<>();
        solve(expression, 0, expression.length(), result);
        return new ArrayList<>(result);
    }

    private void solve(String s, int l, int r, Set<String> result) {
        int level = 0;
        List<String> parts = new ArrayList<>();
        int start = l;

        for (int i = l; i < r; i++) {
            char c = s.charAt(i);

            if (c == '{') level++;
            else if (c == '}') level--;

            if (c == ',' && level == 0) {
                parts.add(s.substring(start, i));
                start = i + 1;
            }
        }

        if (!parts.isEmpty()) {
            parts.add(s.substring(start, r));

            for (String part : parts) {
                solve(part, 0, part.length(), result);
            }
            return;
        }

        int open = -1;
        level = 0;

        for (int i = l; i < r; i++) {
            if (s.charAt(i) == '{') {
                if (level == 0) open = i;
                level++;
            } else if (s.charAt(i) == '}') {
                level--;
                if (level == 0) {
                    Set<String> inside = new TreeSet<>();
                    solve(s, open + 1, i, inside);

                    Set<String> suffix = new TreeSet<>();
                    if (i + 1 < r) {
                        solve(s, i + 1, r, suffix);
                    } else {
                        suffix.add("");
                    }

                    String prefix = s.substring(l, open);

                    for (String a : inside) {
                        for (String b : suffix) {
                            result.add(prefix + a + b);
                        }
                    }
                    return;
                }
            }
        }

        if (l < r) {
            result.add(s.substring(l, r));
        }
    }
}