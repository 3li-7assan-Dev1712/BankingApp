package app.netlify.dev_ali_hassan.bankingapp.util

import android.content.Context

class ResourceUtil {
    companion object {
        fun getResourceIdOfAvatar(avatarCode: Int, context: Context): Int =
            context.resources.getIdentifier("avatar_$avatarCode", "drawable", context.packageName)

    }
}