// $Id$
/*
 * WorldEdit
 * Copyright (C) 2010 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package com.sk89q.worldedit;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Double array lists to work like a Map, but not really.
 *
 * @author sk89q
 */
public class DoubleArrayList<A,B> implements Iterable<Map.Entry<A,B>> {
    /**
     * First list.
     */
    private List<A> listA = new ArrayList<A>();
    /**
     * Second list.
     */
    private List<B> listB = new ArrayList<B>();

    /**
     * Add an item.
     * 
     * @param a
     * @param b
     */
    public void put(A a, B b) {
        listA.add(a);
        listB.add(b);
    }

    /**
     * Get size.
     * @return
     */
    public int size() {
        return listA.size();
    }

    /**
     * Clear the list.
     */
    public void clear() {
        listA.clear();
        listB.clear();
    }

    /**
     * Get an entry set.
     *
     * @return
     */
    public Iterator<Map.Entry<A,B>> iterator() {
        return new EntryIterator<Map.Entry<A,B>>(
                listA.listIterator(listA.size()),
                listB.listIterator(listB.size()));
    }

    /**
     * Entry iterator.
     * 
     * @param <A>
     * @param <B>
     */
    public class EntryIterator<T extends Map.Entry> implements Iterator<Map.Entry<A,B>> {
        private ListIterator<A> keyIterator;
        private ListIterator<B> valueIterator;

        public EntryIterator(ListIterator<A> keyIterator, ListIterator<B> valueIterator) {
            this.keyIterator = keyIterator;
            this.valueIterator = valueIterator;
        }

        public boolean hasNext() {
            return keyIterator.hasPrevious();
        }

        public Map.Entry<A,B> next() throws NoSuchElementException {
            return new Entry<A,B>(keyIterator.previous(), valueIterator.previous());
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Class to masquerade as Map.Entry.
     * 
     * @param <A>
     * @param <B>
     */
    public class Entry<A,B> implements Map.Entry<A,B> {
        private A key;
        private B value;

        private Entry(A key, B value) {
            this.key = key;
            this.value = value;
        }

        public A getKey() {
            return key;
        }

        public B getValue() {
            return value;
        }

        public B setValue(B value) {
            throw new UnsupportedOperationException();
        }
    }
}
