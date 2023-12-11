package io.raynelz.task3;

import java.util.ArrayList;
import java.util.List;

interface Iterator<T> {
    boolean hasNext();
    T next();
}

enum ItemType {
    Any,
    Weapon,
    Ring,
    Potion
}

class Item {
    private ItemType type;
    private final String name;

    public Item(ItemType type, String name) {
        this.setType(type);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }
}

class TreasureChestItemIterator implements Iterator<Item> {
    private final TreasureChest chest;
    private final ItemType type;

    private  int idx;

    public TreasureChestItemIterator(TreasureChest chest, ItemType type) {
        this.chest =chest;
        this.type = type;
        this.idx = -1;
    }

    @Override
    public boolean hasNext() {
        return -1 != findNextIdx();
    }

    @Override
    public Item next() {
        idx = findNextIdx();
        if (-1 != idx)
            return chest.getItems().get(idx);

        return null;
    }

    private int findNextIdx() {
        List<Item> items = chest.getItems();
        int tempIdx = idx;
        while (true) {
            tempIdx++;
            if (tempIdx >= items.size()) {
                tempIdx = -1;
                break;
            }

            if (type.equals(ItemType.Any) || items.get(tempIdx).getType().equals(type)) {
                break;
            }
        }
        return tempIdx;
    }
}

class TreasureChest {
    private final List<Item> items;

    public TreasureChest() {
        items = List.of(
                new Item(ItemType.Potion, "Potion of courage"),
                new Item(ItemType.Ring, "Ring of shadows"),
                new Item(ItemType.Potion, "Potion of wisdom"),
                new Item(ItemType.Potion, "Potion of blood"),
                new Item(ItemType.Weapon, "Sword of silver +1"),
                new Item(ItemType.Potion, "Potion of rust"),
                new Item(ItemType.Potion, "Potion of healing"),
                new Item(ItemType.Ring, "Ring of armor"),
                new Item(ItemType.Weapon, "Steel halberd"),
                new Item(ItemType.Weapon, "Dagger of poison")
        );
    }

    public  Iterator<Item> iterator(ItemType itemType) {
        return  new TreasureChestItemIterator(this, itemType);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}

public class Application {
    public static void main(String[] args) {}
}
