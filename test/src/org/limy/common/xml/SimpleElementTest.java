package org.limy.common.xml;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class SimpleElementTest {

    @Test
    public void testSimpleElement() {
        SimpleElement root = new SimpleElement("root");
        new SimpleElement(root, "child1");
        new SimpleElement(root, "child2", "child2-value");
    }

    @Test
    public void testClone() throws Exception {
        SimpleElement root = new SimpleElement("root");
        root.setAttribute("attr1", "attr1-value");
        SimpleElement child1 = new SimpleElement(root, "child1");
        child1.setAttribute("attr2", "attr2-value");
        new SimpleElement(root, "child2", "child2-value");
        SimpleElement cloneRoot = (SimpleElement)root.clone();
        
        assertEquals(1, cloneRoot.getAttributes().size());
        assertEquals(2, cloneRoot.getChildren().size());
        XmlElement cloneChild1 = cloneRoot.getChildren().iterator().next();
        assertEquals(1, cloneChild1.getAttributes().size());
        
    }

    @Test
    public void testsetAttribute() {
        SimpleElement root = new SimpleElement("root");
        root.setAttribute(new SimpleAttribute("name", "value"));
        root.setAttribute("name2", "value2");
        assertEquals(2, root.getAttributes().size());
    }

    @Test
    public void testRemoveChild() {
        SimpleElement root = new SimpleElement("root");
        SimpleElement child1 = new SimpleElement(root, "child1");
        assertEquals(1, root.getChildren().size());
        root.removeChild(child1);
        assertEquals(0, root.getChildren().size());
    }

    @Test
    public void testHasAttributes() {
        SimpleElement root = new SimpleElement("root");
        assertFalse(root.hasAttributes());
        root.setAttribute("name", "value");
        assertTrue(root.hasAttributes());
        root.setAttribute("name2", "value2");
        assertTrue(root.hasAttributes());
    }

    @Test
    public void testHasChildren() {
        SimpleElement root = new SimpleElement("root");
        assertFalse(root.hasChildren());
        new SimpleElement(root, "child1");
        assertTrue(root.hasChildren());
        new SimpleElement(root, "child2");
        assertTrue(root.hasChildren());
    }

    @Test
    public void testGetChildren() {
        SimpleElement root = new SimpleElement("root");
        Collection<XmlElement> children = root.getChildren();
        assertEquals(0, children.size());
        
        new SimpleElement(root, "child1");
        children = root.getChildren();
        assertEquals(1, children.size());
        
        new SimpleElement(root, "child2");
        children = root.getChildren();
        assertEquals(2, children.size());
    }

    @Test
    public void testGetName() {
        SimpleElement root = new SimpleElement("root");
        assertEquals("root", root.getName());
    }

    @Test
    public void testGetValue() {
        SimpleElement root = new SimpleElement("root");
        assertNull(root.getValue());
        SimpleElement child1 = new SimpleElement(root, "child1", "child1-value");
        assertEquals("child1-value", child1.getValue());
    }

    @Test
    public void testGetAttributes() {
        SimpleElement root = new SimpleElement("root");
        Collection<XmlAttribute> atrrs = root.getAttributes();
        assertEquals(0, atrrs.size());
        
        root.setAttribute("at1", "1");
        atrrs = root.getAttributes();
        assertEquals(1, atrrs.size());
        
        root.setAttribute("at1", "2");
        atrrs = root.getAttributes();
        assertEquals(1, atrrs.size());
    }
    
    @Test
    public void testCopyBeforeSelf() {
        
        SimpleElement root = new SimpleElement("root");
        SimpleElement child1 = new SimpleElement(root, "child");
        child1.setAttribute("value", "0");
        XmlElement child2 = child1.copyBeforeSelf();
        child2.setAttribute("value", "1");
        
        assertEquals(2, root.getChildren().size());
        Iterator<XmlElement> it = root.getChildren().iterator();
        XmlElement pChild2 = it.next();
        assertEquals(child2, pChild2);
        XmlElement pChild1 = it.next();
        assertEquals(child1, pChild1);
        
        assertEquals("0", pChild1.getAttribute("value"));
        assertEquals("1", pChild2.getAttribute("value"));
    }

}
