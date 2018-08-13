package finder;

import java.util.HashMap;
import java.util.Map;

public class MapComparator {
    public <K, V> Float compareMap(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> mergeMap = new HashMap<>();
        mergeMap.putAll(map1);
        mergeMap.putAll(map2);

        //Two empty maps are equal
        if (mergeMap.size() == 0) return 100f;

        Integer occurrences = mergeMap.entrySet().stream()
                .mapToInt(mergeEntry ->
                        (isEntryInMap(mergeEntry, map1)
                                && isEntryInMap(mergeEntry, map2))
                                ? 1 : 0)
                .sum();

        return occurrences * 100f / mergeMap.size();
    }

    private <V, K> Boolean isEntryInMap(Map.Entry<K, V> mergeEntry, Map<K, V> map) {
        return map.containsKey(mergeEntry.getKey())
                && map.get(mergeEntry.getKey()).equals(mergeEntry.getValue());
    }
}

