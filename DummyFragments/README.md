# Dummy Fragments
It creates an activity to host 3 fragments. On lifecycle call back methods it follows lifecycle of fragments and host activity by logging.

Testing scenarios are as follows:
## Scenario 1:
1. Open activity, let it layout the first fragment.
2. Click navigation bar to navigate to other fragments one by one.
3. Click back button until you close the app.

### Scenario 1 with `replace(..)` method used
```
supportFragmentManager.beginTransaction()
            .replace(binding.navContainer.id, fragment)
            .commit()
```
#### Step 1: Application is opened
```
HostActivity:: onCreate
HomeFragment:: onAttach
HomeFragment:: onCreate
HomeFragment:: onCreateView
HomeFragment:: onActivityCreated
HostActivity:: onStart
HostActivity:: onResume
```
#### Step 2: Navigated to Dashboard fragment
```
DashboardFragment:: onAttach
DashboardFragment:: onCreate
HomeFragment:: onPause
HomeFragment:: onStop
HomeFragment:: onDestroyView
HomeFragment:: onDestroy
DashboardFragment:: onCreateView
DashboardFragment:: onActivityCreated
```
#### Step 2: Navigated to Notification fragment
```
NotificationsFragment:: onAttach
NotificationsFragment:: onCreate
DashboardFragment:: onPause
DashboardFragment:: onStop
DashboardFragment:: onDestroyView
DashboardFragment:: onDestroy
NotificationsFragment:: onCreateView
NotificationsFragment:: onActivityCreated
```
#### Step 3: Back button is clicked
```
NotificationsFragment:: onPause
HostActivity:: onPause
NotificationsFragment:: onStop
HostActivity:: onStop
NotificationsFragment:: onDestroyView
NotificationsFragment:: onDestroy
HostActivity:: onDestroy
```

### Scenario 1 with `replace(..)` method used
```
supportFragmentManager.beginTransaction()
            .replace(binding.navContainer.id, fragment)
            .commit()
``` 

## Scenario 2:
1. Open activity, let it layout the first fragment.
2. Rotate the device.
3. Click navigation bar to navigate to other fragments one by one.
4. Rotate the device.
5. Click back button until you close the app.

## Scenario 1:
