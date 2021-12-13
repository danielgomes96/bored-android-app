import com.devlabs.data.database.model.ActivityLocal
import com.devlabs.data.mapper.BaseMapper
import com.devlabs.domain.entity.Activity
import java.util.*

class ActivityDomainMapper : BaseMapper<List<ActivityLocal>, List<Activity>>() {
    override fun transform(entity: List<ActivityLocal>): List<Activity> {
        return entity.map {
            Activity(
                it.activity,
                it.type,
                it.participants,
                it.price,
                it.link,
                it.key,
                it.accessibility,
                it.progress,
                Date(it.startTime),
                Date(it.endTime)
            )
        }
    }
}