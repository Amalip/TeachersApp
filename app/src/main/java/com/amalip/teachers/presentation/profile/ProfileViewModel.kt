package com.amalip.teachers.presentation.profile

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.usecase.DoLogout
import com.amalip.teachers.domain.usecase.GetLocalUser
import com.amalip.teachers.domain.usecase.UpdateProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject
import kotlin.random.Random

@DelicateCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val updateProfile: UpdateProfile,
    private val doLogout: DoLogout
) : BaseViewModel() {

    var user = MutableLiveData(User())
    var image = MutableLiveData("")

    init {
        getLocalUser(UseCase.None()) {
            it.fold({}) {
                user.value = it
                image.value = it.picture

                true
            }
        }
    }

    fun getRandomImage() {
        image.value = when (Random.nextInt(0, 6)) {
            1 -> "https://shantazy.com/wp-content/uploads/2021/10/efootball-messi-memes.jpg"
            2 -> "https://media.minutouno.com/p/cd1995fdce028fc4c65f6da91464445d/adjuntos/150/imagenes/039/341/0039341374/memes-messi-pochettinojpg.jpg"
            3 -> "https://phantom-marca.unidadeditorial.es/305edc092a3ad9919d4d3b71e123dbb7/assets/multimedia/imagenes/2020/05/28/15906513136141.jpg"
            4 -> "https://cronicaglobal.elespanol.com/uploads/s1/39/83/37/Captura%20de%20pantalla%202017-03-30%20a%20las%2013.03.52.png"
            5 -> "https://images.ole.com.ar/2021/10/22/fd6_DKecj_1200x630__1.jpg"
            6 -> "https://www.mundodeportivo.com/files/image_449_220/files/fp/uploads/2021/12/06/61adeda1eb080.r_d.760-380-2867.jpeg"
            else -> "https://imgresizer.eurosport.com/unsafe/1200x0/filters:format(jpeg):focal(1327x361:1329x359)/origin-imgresizer.eurosport.com/2020/12/02/2948078-60517528-2560-1440.jpg"
        }
    }

    fun editUser(data: User) {
        if (data.name.isNotEmpty()
            && data.firstLastname.isNotEmpty()
            && data.secondLastname.isNotEmpty()
            && data.email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(data.email).matches()
        ) {
            updateProfile(user.value?.apply { picture = image.value ?: picture } ?: data) {
                it.fold(::handleFailure) {
                    state.value = ProfileViewState.Updated

                    true
                }
            }

        } else failure.value = ProfileFailure.CompleteForm

    }

    fun logout() {
        doLogout(UseCase.None()) {
            it.fold(::handleFailure) {
                state.value = ProfileViewState.LoggedOut

                true
            }
        }
    }

}