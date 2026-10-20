package com.danielrosehill.parlance

/**
 * Parlance's links: the source repo, its issue tracker, and Hebrish (the sister app for the
 * code-switching model, whose website and terms cover that model). The only place these URLs
 * live; the Help and links screen is built from [LINKS]. A `mailto:` entry, if one is ever added,
 * is opened as an email with the app and device version appended (Page.open).
 */
object Links {
    const val SOURCE = "https://github.com/danielrosehill/Parlance-Dictation"
    const val ISSUES = "$SOURCE/issues/new"
    const val HEBRISH = "https://hebrish.app"

    /** (button label, URL), in display order. */
    val LINKS = listOf(
        R.string.link_bug to ISSUES,
        R.string.link_feature to ISSUES,
        R.string.link_hebrish to HEBRISH,
    )
}
