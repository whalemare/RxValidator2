package ru.whalemare.sample.rxvalidator.rule

import java.util.regex.Pattern

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class RegexRule(regex: String) : PatternRule(Pattern.compile(regex))