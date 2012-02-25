/*
 * Created 2007/07/07
 * Copyright (C) 2003-2007  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of limyweb-common.
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
package org.limy.common.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * XML関連のユーティリティクラスです。
 * @author Naoki Iwami
 */
public final class XmlUtils {
    
    /** logger */
    private static final Log LOG = LogFactory.getLog(XmlUtils.class);

    /** XMLファクトリ */
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
    
    /**
     * private constructor
     */
    private XmlUtils() { }
    
    /**
     * XMLをパースしてDOM Documentを返します。
     * @param in XML入力ストリーム
     * @return DOM Document
     * @throws IOException I/O例外
     */
    public static Document parseDoc(InputStream in) throws IOException {
        try {
            return createBuilder().parse(in);
        } catch (SAXException e) {
            IOException exception = new IOException(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    /**
     * XMLをパースしてXMLElementを返します。
     * @param in XML入力ストリーム
     * @return XMLElement
     * @throws IOException I/O例外
     */
    public static XmlElement parse(InputStream in) throws IOException {
        try {
            Document document = createBuilder().parse(in);
            Element root = document.getDocumentElement();
            SimpleElement el = new SimpleElement(root.getNodeName());
            parse(el, root);
            return el;
        } catch (SAXException e) {
            IOException exception = new IOException(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    // ------------------------ Private Methods

    private static DocumentBuilder createBuilder() {
        try {
            return FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    private static void parse(XmlElement root, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                root.setAttribute(attr.getNodeName(), attr.getNodeValue());
            }
        }
        
        NodeList children = node.getChildNodes();
        if (children != null) {
            Text text = null;
            boolean isChild = false;
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child instanceof Element) {
                    isChild = true;
                    SimpleElement el = new SimpleElement(root, child.getNodeName());
                    parse(el, child);
                }
                if (child instanceof Text) {
                    text = (Text)child;
                }
            }
            if (!isChild && text != null) {
                // 子要素が一つも無くてテキスト値があった場合
                root.setValue(text.getTextContent());
            }
        }
        
    }
    
}
