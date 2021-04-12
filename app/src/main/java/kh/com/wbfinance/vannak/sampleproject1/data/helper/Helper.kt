package kh.com.wbfinance.vannak.sampleproject1.data.helper

import androidx.annotation.IntDef

object ApiHelper {
}

object Constant {
    const val API_VERSION = "1.0.0"
    const val DB_VERSION = 1
    const val NETWORK_TIMEOUT = 60L
    const val ERROR_MESSAGE = "Cannot proceed your request, please try again later"
    const val UPDATE_ERROR_MESSAGE = "Cannot get latest update"
}

object UIConstant {
    const val COUNTRY = "country"
}

object CacheKey {
    const val ASEAN = "cache_asean_countries"
    const val OVERVIEW = "cache_statistics"
    const val CONTINENT = "cache_continents"
    const val COUNTRY = "cache_country"
}


@IntDef(CaseType.CONFIRMED, CaseType.DEATHS, CaseType.RECOVERED, CaseType.FULL)
@Retention(AnnotationRetention.SOURCE)
annotation class CaseTypes

object CaseType {
    const val CONFIRMED = 0
    const val DEATHS = 1
    const val RECOVERED = 2
    const val FULL = 3
}

object IncrementStatus {
    const val FLAT = 0
    const val INCREASE = 1
    const val DECREASE = 2
}