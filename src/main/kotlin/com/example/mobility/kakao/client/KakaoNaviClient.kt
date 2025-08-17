package com.example.mobility.kakao.client

import com.example.mobility.global.dto.composeWaypoint
import com.example.mobility.global.dto.composeWaypoints
import com.example.mobility.kakao.dto.req.ClientRequest
import com.example.mobility.kakao.dto.res.KakaoDirectionsResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import java.net.URI
import java.time.Duration
import kotlin.reflect.full.memberProperties

@Service
class KakaoNaviClient(
    // 카카오네비/맵 등의 유사한 Bean종류가 있으므로, @Autowired 보다 @Qualifier로 지정해서 연결
    @Qualifier("kakaoNaviWebClient")
    private val wc: WebClient
) {
    private val timeout: Duration = Duration.ofSeconds(5)

    fun UriBuilder.kakaoDirectionsUri(req: ClientRequest): URI {
        return this.path("/v1/directions")
            .apply {
                queryParam("origin", composeWaypoint(req.origin))
                queryParam("destination", composeWaypoint(req.destination))
                if (req.waypoints.isNotEmpty()) {
                    queryParam("waypoint", composeWaypoints(req.waypoints))
                }

                // Reflection으로 나머지 프로퍼티 자동 추가
                ClientRequest::class.memberProperties.forEach { prop ->
                    val name = prop.name
                    val value = prop.get(req)

                    // origin/destination/waypoints는 이미 처리했으니 제외
                    if (name in listOf("origin", "destination", "waypoints")) return@forEach

                    // null 아닌 경우만 추가
                    if (value != null) {
                        queryParam(name, value)
                    }
                }
            }
            .build()
    }

    fun oneToOneApi(req: ClientRequest) : KakaoDirectionsResponse =
        wc.get()
            .uri { b ->
                b.kakaoDirectionsUri(req)
            }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus({ it.isError }) { r -> r.bodyToMono<String>().flatMap { Mono.error(RuntimeException("Kakao /v1/directions error: $it")) } }
            .bodyToMono<KakaoDirectionsResponse>()
            .timeout(timeout)
            .block() ?: KakaoDirectionsResponse()
}
