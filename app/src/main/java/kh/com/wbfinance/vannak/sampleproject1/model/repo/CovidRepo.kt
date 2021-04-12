package kh.com.wbfinance.vannak.sampleproject1.model.repo

import io.reactivex.Completable
import io.reactivex.Observable
import kh.com.wbfinance.vannak.sampleproject1.data.base.BaseResult
import kh.com.wbfinance.vannak.sampleproject1.data.pref.CovidPref
import kh.com.wbfinance.vannak.sampleproject1.model.dao.*
import kh.com.wbfinance.vannak.sampleproject1.model.datasource.CovidDataSource

open class CovidRepo constructor(
        private val api: CovidDataSource,
        private val cache: CovidPref
): Repository{

    override fun aseanCountries(): Observable<BaseResult<List<Country>>> {
        val cacheAsean = cache.getAseanCountries()
        val localObservable = if(cacheAsean != null) Observable.just(BaseResult(cacheAsean,null)) else Observable.empty()
        val remoteObservable = api.aseanCountries()
            .flatMap {
                cache.setAseanCountries(it)
                Observable.just(BaseResult(it,null))
            }
            .onErrorResumeNext { t: Throwable ->
                return@onErrorResumeNext if(cacheAsean != null) Observable.just(BaseResult(cacheAsean, t))
                else Observable.error(t)
            }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun specificCountry(country: String): Observable<BaseResult<Country>> {
        val cacheCountry = cache.getCountry(country)
        val localObservable = if(cacheCountry != null) Observable.just(BaseResult(cacheCountry,null)) else Observable.empty()
        val remoteObservable = api.specificCountry(country)
                .flatMap {
                    cache.setCountry(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext {t: Throwable ->
                    return@onErrorResumeNext if (cacheCountry != null) Observable.just(BaseResult(cacheCountry,t))
                    else Observable.error(t)
                }
        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun allCountries(): Observable<BaseResult<List<Country>>> {
        val cacheAllContries = cache.getCountries()
        val localObservable = if(cacheAllContries != null) Observable.just(BaseResult(cacheAllContries,null)) else Observable.empty()
        val remoteObservable = api.aseanCountries()
                .flatMap {
                    cache.setCountries(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if(cacheAllContries != null) Observable.just(BaseResult(cacheAllContries, t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun multipleCountries(counties: String): Observable<BaseResult<List<Country>>> {
        val cacheAllContries = cache.getCountries()
        val localObservable = if(cacheAllContries != null) Observable.just(BaseResult(cacheAllContries,null)) else Observable.empty()
        val remoteObservable = api.multipleCountries(counties)
                .flatMap {
                    cache.setCountries(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if(cacheAllContries != null) Observable.just(BaseResult(cacheAllContries, t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun pinnedRegion(): Observable<BaseResult<Country>> {
        val prefData = getCachePinnedRegion()
        return Observable.just(BaseResult(prefData,null))
    }

    override fun putPinnedRegion(data: Country): Completable {
        return Completable.create {
            if (cache.setCountry(data)) it.onComplete()
            else it.onError(Throwable("Not able to save"))
        }
    }

    override fun removePinnedRegion(): Completable {
        return Completable.create {
//            if (cache.setCountry(Country(country = "no")))
                it.onComplete()
//            else it.onError(Throwable("Not able to remove"))
        }
    }

    override fun getCachePinnedRegion(): Country? = cache.getCountry("")
    override fun getCacheDaily(): List<CovidDaily> {
        return listOf()
    }

    override fun daily(): Observable<BaseResult<List<CovidDaily>>> {
        return Observable.empty()
    }

    override fun country(name: String): Observable<Country> {
        return Observable.empty()
    }

    override fun recovered(): Observable<List<Country>> {
        return Observable.empty()
    }

    override fun deaths(): Observable<List<Country>> {
        return Observable.empty()
    }

    override fun confirmed(): Observable<List<Country>> {
        return Observable.empty()
    }

    override fun fullStats(): Observable<List<Country>> {
        // TODO: 4/12/21
        return Observable.empty()
    }

    override fun overview(): Observable<BaseResult<CovidOverview>> {
        val cacheOverview = cache.getOverview()
        val localObservable =
                if (cacheOverview != null) Observable.just(BaseResult(cacheOverview, null))
                else Observable.empty()

        val remoteObservable = api.overview()
                .flatMap {
                    cache.setOverview(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if (cacheOverview != null) Observable.just(BaseResult(cacheOverview,t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun allContinents(): Observable<BaseResult<List<Continent>>> {
        val cacheContinent = cache.getContinents()
        val localObservable = if(cacheContinent != null) Observable.just(BaseResult(cacheContinent,null)) else Observable.empty()
        val remoteObservable = api.allContinents()
                .flatMap {
                    cache.setContinents(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if(cacheContinent != null) Observable.just(BaseResult(cacheContinent, t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun specificContinent(continent: String): Observable<BaseResult<Continent>> {
        val cacheContinent = cache.getContinent(continent)
        val localObservable = if(cacheContinent != null) Observable.just(BaseResult(cacheContinent,null)) else Observable.empty()
        val remoteObservable = api.specificContinent(continent)
                .flatMap {
                    cache.setContinent(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if(cacheContinent != null) Observable.just(BaseResult(cacheContinent, t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }

    override fun multipleContinents(continents: String): Observable<BaseResult<List<Continent>>> {
        val cacheContinent = cache.getContinents()
        val localObservable = if(cacheContinent != null) Observable.just(BaseResult(cacheContinent,null)) else Observable.empty()
        val remoteObservable = api.multipleContinents(continents)
                .flatMap {
                    cache.setContinents(it)
                    Observable.just(BaseResult(it,null))
                }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext if(cacheContinent != null) Observable.just(BaseResult(cacheContinent, t))
                    else Observable.error(t)
                }

        return Observable.concatArrayEager(localObservable, remoteObservable)
    }
}