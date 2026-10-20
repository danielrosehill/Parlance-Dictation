package com.danielrosehill.parlance

import com.danielrosehill.parlance.core.Catalogue

/** Help and links: bug reports and feature requests on GitHub, Hebrish, the model card, source and terms. */
class ContactActivity : Page() {
    override fun build() {
        text(R.string.contact_body)
        // Built from Links.LINKS, so adding or retiring a link touches only Links.kt and its label.
        for ((label, url) in Links.LINKS) button(label) { open(url) }

        heading(R.string.links)
        val r = row()
        button(R.string.open_model, r) {
            val m = Catalogue.selected(this)
            open(m.card ?: "https://huggingface.co/${m.repo}")
        }
        button(R.string.open_source, r) { open(getString(R.string.url_source)) }

        heading(R.string.terms_title)
        text(R.string.terms_body)
        button(R.string.open_terms) { open(getString(R.string.url_terms)) }
    }
}
