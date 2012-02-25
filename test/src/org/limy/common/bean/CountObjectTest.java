/*
 * Created 2006/07/10
 * Copyright (C) 2003-2006  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limy-core.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.limy.common.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CountObjectTest {

    @Test
    public void testAll() {
        
        CountObject obj1 = new CountObject("abc");
        assertEquals("abc", obj1.getStr());
        assertEquals(0, obj1.getCount());
        
        obj1.increment();
        obj1.increment();
        assertEquals(2, obj1.getCount());
        
        assertTrue(obj1.toString().indexOf("@") < 0);
        
        CountObject obj2 = new CountObject(null);
        assertTrue(obj2.toString().indexOf("@") < 0);
        
        List<CountObject> results = new ArrayList<CountObject>();
        results.add(obj1);
        results.add(obj2);
        
        // countでソートされるので obj2, obj1 の順になる
        Collections.sort(results);
        
        assertEquals(obj2, results.get(0));
        assertEquals(obj1, results.get(1));
        
        Set<CountObject> set = new HashSet<CountObject>();
        set.add(obj1);
        set.add(obj2);
        
        System.out.println("-- testAll3 --");
    }

}
