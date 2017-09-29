import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.junit.Assert.assertThat
import org.junit.Test
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory


class MatchersTest {

    @Test
    fun shouldReadXml() {
        val xmlReader = XmlStringReader()

        val document = xmlReader.read("""
            <!DOCTYPE xml>
            <root>
                <child>Child Node</child>
            </root>
        """)

        assertThat(document, Matchers.hasXPath("//root/child"))
        assertThat(document, hasDocType("html"))
    }

}

class XmlStringReader {
    fun read(xml: String): Document {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        return builder.parse(xml.byteInputStream())
    }
}

fun hasDocType(expected: String): Matcher<Document> {
    return DocTypeMatcher(expected)
}

class DocTypeMatcher(val expected: String) : TypeSafeDiagnosingMatcher<Document>() {

    override fun describeTo(description: Description) {
        description.appendText("doctype to be $expected")
    }

    override fun matchesSafely(document: Document, mismatchDescription: Description): Boolean {
        if(document.doctype == null) {
            mismatchDescription.appendText("no doctype was set")
            return false
        }

        mismatchDescription.appendText("was ${document.doctype.name}")
        return document.doctype.name == expected
    }
}


